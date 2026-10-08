package ru.learn.javapracticehw.budget.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.budget.dto.BudgetCreateDto;
import ru.learn.javapracticehw.budget.dto.BudgetExecutionResponseDto;
import ru.learn.javapracticehw.budget.dto.BudgetResponseDto;
import ru.learn.javapracticehw.budget.entity.Budget;
import ru.learn.javapracticehw.budget.exception.BudgetNotFoundException;
import ru.learn.javapracticehw.budget.mapper.BudgetMapper;
import ru.learn.javapracticehw.budget.repository.BudgetRepository;
import ru.learn.javapracticehw.budget.service.BudgetService;
import ru.learn.javapracticehw.category.service.CategoryService;
import ru.learn.javapracticehw.user.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryService categoryService;
    private final BudgetMapper budgetMapper;

    @Override
    public BudgetResponseDto createBudget(BudgetCreateDto dto, User currentUser) {

        var budget = Budget.builder()
            .amount(dto.amount())
            .department(currentUser.getDepartment())
            .category(categoryService.getCategoryEntityById(dto.categoryId()))
            .startDate(dto.startDate())
            .endDate(dto.endDate())
            .build();

        budgetRepository.save(budget);
        return budgetMapper.toBudgetResponseDto(budget);
    }

    @Override
    public BudgetExecutionResponseDto getBudgetExecution(UUID budgetId, User currentUser) {
        var budget = budgetRepository
            .findById(budgetId)
            .orElseThrow(() -> new BudgetNotFoundException("Бюджет с id: %s не найден".formatted(budgetId)));

        Instant startDate = budget.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endDate = budget.getEndDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
        BigDecimal actual = budgetRepository.calculateExpense(
            currentUser.getDepartment().getId(),
            budget.getCategory().getId(),
            startDate,
            endDate
        );

        BigDecimal remainingAmount = budget.getAmount().subtract(actual != null ? actual : BigDecimal.ZERO);

        return new BudgetExecutionResponseDto(
            budget.getId(),
            budget.getCategory().getId(),
            budget.getAmount(),
            actual,
            remainingAmount
        );
    }
}
