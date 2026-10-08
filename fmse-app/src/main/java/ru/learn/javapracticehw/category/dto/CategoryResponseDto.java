package ru.learn.javapracticehw.category.dto;

import java.util.UUID;

public record CategoryResponseDto(
    UUID id,
    String name,
    CategoryResponseDto parentCategory
) {
}
