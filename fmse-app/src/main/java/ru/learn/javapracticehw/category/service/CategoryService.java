package ru.learn.javapracticehw.category.service;

import ru.learn.javapracticehw.category.entity.Category;

import java.util.UUID;

public interface CategoryService {
    Category getCategoryEntityById(UUID categoryId);
}
