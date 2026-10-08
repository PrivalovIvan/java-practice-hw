package ru.learn.javapracticehw.account.service;

import ru.learn.javapracticehw.account.dto.AccountCreatedDto;
import ru.learn.javapracticehw.account.dto.AccountResponseDto;
import ru.learn.javapracticehw.account.entity.Account;
import ru.learn.javapracticehw.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponseDto createAccount(AccountCreatedDto dto, User currentUser);

    List<AccountResponseDto> getAllMyDepartmentAccounts(User currentUser);

    Account getAccountById(UUID accountId, User currentUser);
}
