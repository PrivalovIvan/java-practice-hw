package ru.learn.javapracticehw.category.exception;

import ru.learn.javapracticehw.common.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
