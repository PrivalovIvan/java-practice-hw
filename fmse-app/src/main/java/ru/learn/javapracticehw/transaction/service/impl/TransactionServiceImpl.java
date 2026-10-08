package ru.learn.javapracticehw.transaction.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learn.javapracticehw.account.service.AccountService;
import ru.learn.javapracticehw.audit.aspect.Auditable;
import ru.learn.javapracticehw.audit.entity.AuditLog;
import ru.learn.javapracticehw.category.service.CategoryService;
import ru.learn.javapracticehw.common.enums.StatusTransaction;
import ru.learn.javapracticehw.counterpart.service.CounterpartService;
import ru.learn.javapracticehw.transaction.dto.TransactionCreateDto;
import ru.learn.javapracticehw.transaction.dto.TransactionResponseDto;
import ru.learn.javapracticehw.transaction.entity.Transaction;
import ru.learn.javapracticehw.transaction.exception.TransactionNotFound;
import ru.learn.javapracticehw.transaction.mapper.TransactionMapper;
import ru.learn.javapracticehw.transaction.repository.TransactionRepository;
import ru.learn.javapracticehw.transaction.service.TransactionService;
import ru.learn.javapracticehw.user.entity.User;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final CounterpartService counterpartService;

    @Override
    @Auditable(action = AuditLog.Action.CREATION)
    public TransactionResponseDto createDraft(TransactionCreateDto dto, User currentUser) {

        var fromAccount = dto.fromAccount() != null
            ? accountService.getAccountById(dto.fromAccount(), currentUser)
            : null;

        var category = dto.category() != null
            ? categoryService.getCategoryEntityById(dto.category())
            : null;

        var toAccount = dto.toAccount() != null
            ? accountService.getAccountById(dto.toAccount(), currentUser)
            : null;
        var counterpart = dto.counterpart() != null
            ? counterpartService.getCounterpartEntityById(dto.counterpart())
            : null;

        var transaction = new Transaction(
            dto.type(), dto.amount(), dto.currency(), fromAccount, toAccount, category, counterpart, currentUser
        );
        transactionRepository.save(transaction);
        return transactionMapper.toTransactionResponseDto(transaction);
    }

    @Override
    @Auditable(action = AuditLog.Action.CONFIRMATION)
    public TransactionResponseDto confirmTransaction(UUID transactionId, User currentUser) {
        var transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionNotFound("Транзакция с id: %s не найдена"));

        if (transaction.getStatus() == StatusTransaction.CONFIRMED) {
            throw new IllegalStateException("Транзакция уже подтверждена");
        }

        transaction.confirm();
        processTransaction(transaction);
        return transactionMapper.toTransactionResponseDto(transaction.confirm());
    }

    @Override
    @Auditable(action = AuditLog.Action.STORN)
    public TransactionResponseDto stornoTransaction(UUID transactionId, User currentUser) {
        Transaction originalTx = transactionRepository.findById(transactionId)
            .orElseThrow();
        if (originalTx.getStatus() != StatusTransaction.CONFIRMED) {
            throw new IllegalStateException("Сторнировать можно только подтвержденные транзакции");
        }
        Transaction stornoTx = originalTx.createStorno(currentUser);
        transactionRepository.save(stornoTx);

        processTransaction(stornoTx);

        return transactionMapper.toTransactionResponseDto(stornoTx);
    }

    private void processTransaction(Transaction stornoTx) {
        switch (stornoTx.getType()) {
            case TRANSFER -> {
                stornoTx.getFromAccount().withdrawFunds(stornoTx.getAmount());
                stornoTx.getToAccount().addFunds(stornoTx.getAmount());
            }
            case INCOME -> {
                stornoTx.getToAccount().addFunds(stornoTx.getAmount());
            }
            case EXPENSE -> {
                stornoTx.getFromAccount().withdrawFunds(stornoTx.getAmount());
            }
        }
    }
}
