package ru.learn.javapracticehw.report.dto;

import ru.learn.javapracticehw.common.enums.TypeTransaction;

import java.math.BigDecimal;

public record CategoryFlowDto(
    String categoryName,
    TypeTransaction type,
    BigDecimal totalAmount
) {
}
