package ru.learn.javapracticehw.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.category.entity.Category;
import ru.learn.javapracticehw.category.exception.CategoryNotFoundException;
import ru.learn.javapracticehw.category.repository.CategoryRepository;
import ru.learn.javapracticehw.category.service.CategoryService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryEntityById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException("Категория с id: %s не найдена".formatted(categoryId)));
    }
}
