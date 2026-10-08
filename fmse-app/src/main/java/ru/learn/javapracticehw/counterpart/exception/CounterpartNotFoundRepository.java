package ru.learn.javapracticehw.counterpart.exception;

import ru.learn.javapracticehw.common.exception.NotFoundException;

public class CounterpartNotFoundRepository extends NotFoundException {
    public CounterpartNotFoundRepository(String message) {
        super(message);
    }
}
