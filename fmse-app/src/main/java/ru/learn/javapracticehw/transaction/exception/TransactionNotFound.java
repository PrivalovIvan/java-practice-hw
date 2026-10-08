package ru.learn.javapracticehw.transaction.exception;

import ru.learn.javapracticehw.common.exception.NotFoundException;

public class TransactionNotFound extends NotFoundException {
    public TransactionNotFound(String message) {
        super(message);
    }
}
