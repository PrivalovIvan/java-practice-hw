package ru.learn.javapracticehw.budget.service;

import ru.learn.javapracticehw.budget.dto.BudgetCreateDto;
import ru.learn.javapracticehw.budget.dto.BudgetExecutionResponseDto;
import ru.learn.javapracticehw.budget.dto.BudgetResponseDto;
import ru.learn.javapracticehw.user.entity.User;

import java.util.UUID;

public interface BudgetService {
    BudgetResponseDto createBudget(BudgetCreateDto dto, User currentUser);

    BudgetExecutionResponseDto getBudgetExecution(UUID budgetId, User currentUser);
}
