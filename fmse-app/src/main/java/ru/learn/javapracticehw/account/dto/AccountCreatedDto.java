package ru.learn.javapracticehw.account.dto;

import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.TypeAccount;

public record AccountCreatedDto(
    TypeAccount type,
    Currency currency
) {
}
