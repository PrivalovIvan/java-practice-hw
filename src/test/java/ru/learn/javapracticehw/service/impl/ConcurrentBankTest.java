package ru.learn.javapracticehw.service.impl;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentBankTest {

    @Test
    void successfulTransfer_shouldUpdateBalancesAndKeepTotal() {
        // given
        ConcurrentBank bank = new ConcurrentBank();
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // when
        bank.transfer(account1, account2, 300);

        // then
        assertEquals(700, account1.getBalance());
        assertEquals(800, account2.getBalance());
        assertEquals(1500, bank.getTotalBalance());
    }

    @Test
    void concurrentCrossTransfers_shouldNotDeadlockAndKeepTotalBalance() throws InterruptedException {
        // given
        ConcurrentBank bank = new ConcurrentBank();
        BankAccount account1 = bank.createAccount(10000);
        BankAccount account2 = bank.createAccount(10000);

        int totalTransfers = 100;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(totalTransfers);

        // when
        try (pool) {
            for (int i = 0; i < totalTransfers; i++) {
                boolean isEven = (i % 2 == 0);

                pool.submit(() -> {
                    try {
                        if (isEven) {
                            bank.transfer(account1, account2, 100);
                        } else {
                            bank.transfer(account2, account1, 100);
                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }
        }
        boolean completed = latch.await(5, TimeUnit.SECONDS);
        pool.shutdown();

        // then
        assertTrue(completed);

        assertEquals(10000, account1.getBalance());
        assertEquals(10000, account2.getBalance());

        assertEquals(20000, bank.getTotalBalance());
    }
}
