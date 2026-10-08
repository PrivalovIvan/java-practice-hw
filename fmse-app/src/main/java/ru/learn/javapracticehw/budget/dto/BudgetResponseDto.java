package ru.learn.javapracticehw.budget.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BudgetResponseDto(
    UUID budgetId,
    UUID categoryId,
    UUID departmentId,
    BigDecimal amount,
    LocalDate startDate,
    LocalDate endDate
) {
}
