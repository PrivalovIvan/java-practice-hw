package ru.learn.javapracticehw.budget.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BudgetCreateDto(
    @NotNull UUID categoryId,
    @NotNull @Positive BigDecimal amount,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate
) {
}
