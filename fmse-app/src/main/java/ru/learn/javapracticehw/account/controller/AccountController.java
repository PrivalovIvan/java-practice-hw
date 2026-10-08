package ru.learn.javapracticehw.account.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.account.dto.AccountCreatedDto;
import ru.learn.javapracticehw.account.dto.AccountResponseDto;
import ru.learn.javapracticehw.account.service.AccountService;
import ru.learn.javapracticehw.security.model.SecurityUser;
import ru.learn.javapracticehw.user.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> createAccount(
        @Valid @RequestBody AccountCreatedDto dto,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {
        User user = securityUser.user();
        var createdAccount = accountService.createAccount(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAllMyDepartmentAccounts(
        @AuthenticationPrincipal SecurityUser securityUser
    ) {
        return ResponseEntity.ok(accountService.getAllMyDepartmentAccounts(securityUser.user()));
    }
}
