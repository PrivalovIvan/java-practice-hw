package ru.learn.javapracticehw.exception;

public class NotEnoughMoneyInTheAccountException extends RuntimeException {
    public NotEnoughMoneyInTheAccountException(String message) {
        super(message);
    }
}
