package ru.learn.javapracticehw.service;

public interface BankAccountService {

    void deposit(long deposit);

    void withdraw(long deposit);

    long getBalance();
}
