package ru.learn.javapracticehw.budget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.budget.dto.BudgetResponseDto;
import ru.learn.javapracticehw.budget.entity.Budget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BudgetMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "budgetId", source = "id")
    BudgetResponseDto toBudgetResponseDto(Budget budget);
}
