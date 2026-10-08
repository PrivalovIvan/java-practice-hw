package ru.learn.javapracticehw.transaction.dto;

import ru.learn.javapracticehw.account.entity.Account;
import ru.learn.javapracticehw.category.entity.Category;
import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.StatusTransaction;
import ru.learn.javapracticehw.common.enums.TypeTransaction;
import ru.learn.javapracticehw.counterpart.entity.Counterpart;
import ru.learn.javapracticehw.user.entity.User;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponseDto(
    UUID id,
    TypeTransaction type,
    BigDecimal amount,
    Currency currency,
    Account fromAccount,
    Account toAccount,
    Category category,
    Counterpart counterpart,
    StatusTransaction status,
    User author
) {
}
