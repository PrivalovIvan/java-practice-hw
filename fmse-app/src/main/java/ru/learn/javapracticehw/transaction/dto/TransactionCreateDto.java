package ru.learn.javapracticehw.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.TypeTransaction;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record TransactionCreateDto(
    @NotNull(message = "Тип транзакции обязателен")
    TypeTransaction type,

    @NotNull(message = "Сумма обязательна")
    @Positive(message = "Сумма должна быть больше нуля")
    BigDecimal amount,

    @NotNull(message = "Валюта обязательна")
    Currency currency,

    UUID fromAccount,
    UUID toAccount,
    UUID category,
    UUID counterpart
) {
}
