package ru.learn.javapracticehw.report.dto;

import java.math.BigDecimal;
import java.util.List;

public record CashFlowReportDto(
    BigDecimal totalIncome,
    BigDecimal totalExpense,
    List<CategoryFlowDto> details
) {
}
