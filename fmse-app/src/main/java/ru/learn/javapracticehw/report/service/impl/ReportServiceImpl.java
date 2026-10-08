package ru.learn.javapracticehw.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.common.enums.TypeTransaction;
import ru.learn.javapracticehw.report.dto.CashFlowReportDto;
import ru.learn.javapracticehw.report.dto.CategoryFlowDto;
import ru.learn.javapracticehw.report.service.ReportService;
import ru.learn.javapracticehw.transaction.repository.TransactionRepository;
import ru.learn.javapracticehw.user.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TransactionRepository transactionRepository;

    @Override
    public CashFlowReportDto getCashFlowReport(LocalDate startDate, LocalDate endDate, User currentUser) {
        Instant start = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = endDate.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();

        List<CategoryFlowDto> cashFlowByCategory = transactionRepository.getCashFlowByCategory(
            currentUser.getDepartment().getId(), start, end
        );

        BigDecimal totalIncome = calculate(cashFlowByCategory, TypeTransaction.INCOME);
        BigDecimal totalExpense = calculate(cashFlowByCategory, TypeTransaction.EXPENSE);

        return new CashFlowReportDto(totalIncome, totalExpense, cashFlowByCategory);
    }

    private BigDecimal calculate(List<CategoryFlowDto> cashFlowByCategory, TypeTransaction type) {
        return cashFlowByCategory.stream()
            .filter(f -> f.type() == type)
            .map(CategoryFlowDto::totalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
