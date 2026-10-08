package ru.learn.javapracticehw.budget.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BudgetExecutionResponseDto(
    UUID budgetId,
    UUID categoryId,
    BigDecimal planAmount,
    BigDecimal actualAmount,
    BigDecimal remainingAmount
) {
}
