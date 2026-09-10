package ru.learn.javapracticehw.service.impl;

import ru.learn.javapracticehw.exception.NotEnoughMoneyInTheAccountException;
import ru.learn.javapracticehw.service.BankAccountService;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount implements BankAccountService {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final UUID id;
    private long deposit;

    public BankAccount(long deposit) {
        this.id = UUID.randomUUID();
        this.deposit = deposit;
    }

    @Override
    public void deposit(long deposit) {
        lock.writeLock().lock();
        try {
            this.deposit += deposit;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void withdraw(long deposit) {
        lock.writeLock().lock();
        try {
            if (deposit > this.deposit) {
                throw new NotEnoughMoneyInTheAccountException("Недостаточно денег на счёте");
            }
            this.deposit -= deposit;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public long getBalance() {
        lock.readLock().lock();
        try {
            return deposit;
        } finally {
            lock.readLock().unlock();
        }
    }

    public UUID getId() {
        return id;
    }

    public Lock getWriteLock() {
        return lock.writeLock();
    }
}
