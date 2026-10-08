package ru.learn.javapracticehw.budget.exception;

import ru.learn.javapracticehw.common.exception.NotFoundException;

public class BudgetNotFoundException extends NotFoundException {
    public BudgetNotFoundException(String message) {
        super(message);
    }
}
