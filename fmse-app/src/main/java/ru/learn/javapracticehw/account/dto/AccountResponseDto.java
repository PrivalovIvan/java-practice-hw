package ru.learn.javapracticehw.account.dto;

import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.TypeAccount;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponseDto(
    UUID id,
    TypeAccount type,
    Currency currency,
    BigDecimal balance
) {
}
