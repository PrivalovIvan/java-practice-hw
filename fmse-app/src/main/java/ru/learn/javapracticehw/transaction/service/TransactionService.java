package ru.learn.javapracticehw.transaction.service;

import ru.learn.javapracticehw.transaction.dto.TransactionCreateDto;
import ru.learn.javapracticehw.transaction.dto.TransactionResponseDto;
import ru.learn.javapracticehw.user.entity.User;

import java.util.UUID;

public interface TransactionService {

    // 1. Создать черновик (DRAFT). Баланс счетов НЕ меняется.
    TransactionResponseDto createDraft(TransactionCreateDto dto, User currentUser);

    // 2. Подтвердить операцию (CONFIRMED). Вот тут меняются балансы счетов!
    // Обязательна аннотация @Transactional над реализацией
    TransactionResponseDto confirmTransaction(UUID transactionId, User currentUser);

    // 3. Сторнировать операцию.
    // Если операция CONFIRMED, мы не можем ее удалить или изменить.
    // Мы создаем НОВУЮ операцию с отрицательной суммой (или меняем счета местами)
    // и статусом CANCELLED (или специальным типом STORNO), которая возвращает баланс как было.
    TransactionResponseDto stornoTransaction(UUID transactionId, User currentUser);
}
