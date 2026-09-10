package ru.learn.javapracticehw.service.impl;

import ru.learn.javapracticehw.exception.NotEnoughMoneyInTheAccountException;
import ru.learn.javapracticehw.service.ConcurrentBankService;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcurrentBank implements ConcurrentBankService {
    private final Logger log = Logger.getLogger(ConcurrentBank.class.getName());
    private final AtomicLong totalBalance = new AtomicLong(0);

    @Override
    public BankAccount createAccount(long sum) {
        var account = new BankAccount(sum);
        totalBalance.addAndGet(sum);
        return account;
    }

    @Override
    public void transfer(BankAccount from, BankAccount to, long sum) {
        var firstLock = from.getId().compareTo(to.getId()) < 0 ? from : to;
        var secondLock = from.getId().compareTo(to.getId()) < 0 ? to : from;

        firstLock.getWriteLock().lock();
        try {
            secondLock.getWriteLock().lock();
            try {
                from.withdraw(sum);
                to.deposit(sum);
            } catch (NotEnoughMoneyInTheAccountException ex) {
                log.log(Level.INFO, ex.getMessage(), ex);
            } finally {
                secondLock.getWriteLock().unlock();
            }
        } finally {
            firstLock.getWriteLock().unlock();
        }
    }

    @Override
    public long getTotalBalance() {
        return totalBalance.get();
    }
}
