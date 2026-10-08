package ru.learn.javapracticehw.account.exception;

import ru.learn.javapracticehw.common.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
