package ru.learn.javapracticehw.service;

import ru.learn.javapracticehw.service.impl.BankAccount;

public interface ConcurrentBankService {
    BankAccount createAccount(long sum);

    void transfer(BankAccount from, BankAccount to, long sum);

    long getTotalBalance();
}
