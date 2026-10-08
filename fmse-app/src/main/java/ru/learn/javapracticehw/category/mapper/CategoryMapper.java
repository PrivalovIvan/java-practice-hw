package ru.learn.javapracticehw.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.category.dto.CategoryResponseDto;
import ru.learn.javapracticehw.category.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryResponseDto toCategoryResponseDto(Category category);
}
