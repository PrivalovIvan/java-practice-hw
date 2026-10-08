package ru.learn.javapracticehw.category.dto;

public record CategoryCreateDto(
    String name,
    CategoryResponseDto parentCategory
) {
}
