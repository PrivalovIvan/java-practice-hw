package ru.learn.javapracticehw.budget.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.budget.dto.BudgetCreateDto;
import ru.learn.javapracticehw.budget.dto.BudgetResponseDto;
import ru.learn.javapracticehw.budget.service.BudgetService;
import ru.learn.javapracticehw.security.model.SecurityUser;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/budgets")
    @PreAuthorize("hasAnyRole('FINANCE_MANAGER', 'ADMIN')")
    public ResponseEntity<BudgetResponseDto> createBudget(
        @Valid @RequestBody BudgetCreateDto budgetDto,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {
        var budget = budgetService.createBudget(budgetDto, securityUser.user());
        return ResponseEntity.status(HttpStatus.CREATED).body(budget);
    }
}
