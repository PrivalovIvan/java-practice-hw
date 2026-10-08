package ru.learn.javapracticehw.transaction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.security.model.SecurityUser;
import ru.learn.javapracticehw.transaction.dto.TransactionCreateDto;
import ru.learn.javapracticehw.transaction.dto.TransactionResponseDto;
import ru.learn.javapracticehw.transaction.service.TransactionService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
    ResponseEntity<TransactionResponseDto> createTransaction(
        @Valid @RequestBody TransactionCreateDto createDto,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {
        var draftTx = transactionService.createDraft(createDto, securityUser.user());
        return ResponseEntity.status(HttpStatus.CREATED).body(draftTx);
    }

    @PostMapping("/transactions/{id}/confirm")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
    ResponseEntity<TransactionResponseDto> confirmTransaction(
        @PathVariable UUID id,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {

        var confirmTx = transactionService.confirmTransaction(id, securityUser.user());
        return ResponseEntity.ok(confirmTx);
    }

    @PostMapping("/transactions/{id}/storno")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'ADMIN')")
    ResponseEntity<TransactionResponseDto> stornoTransaction(
        @PathVariable UUID id,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {

        var confirmTx = transactionService.stornoTransaction(id, securityUser.user());
        return ResponseEntity.ok(confirmTx);
    }
}
