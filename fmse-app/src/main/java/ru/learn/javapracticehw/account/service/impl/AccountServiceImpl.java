package ru.learn.javapracticehw.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.account.dto.AccountCreatedDto;
import ru.learn.javapracticehw.account.dto.AccountResponseDto;
import ru.learn.javapracticehw.account.entity.Account;
import ru.learn.javapracticehw.account.exception.AccessDeniedException;
import ru.learn.javapracticehw.account.exception.AccountNotFoundException;
import ru.learn.javapracticehw.account.mapper.AccountMapper;
import ru.learn.javapracticehw.account.repository.AccountRepository;
import ru.learn.javapracticehw.account.service.AccountService;
import ru.learn.javapracticehw.user.entity.User;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDto createAccount(AccountCreatedDto dto, User currentUser) {
        Account account = new Account(currentUser.getDepartment(), dto.type(), dto.currency());
        accountRepository.save(account);
        return accountMapper.toResponseDto(account);
    }

    @Override
    public List<AccountResponseDto> getAllMyDepartmentAccounts(User currentUser) {
        return accountRepository.findAllByDepartmentId(currentUser.getDepartment().getId())
            .stream().map(accountMapper::toResponseDto)
            .toList();
    }

    @Override
    public Account getAccountById(UUID accountId, User currentUser) {
        var account = accountRepository
            .findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException("Аккаунт с id: %s не найден".formatted(accountId)));

        if (!account.getDepartment().getId().equals(currentUser.getDepartment().getId())) {
            throw new AccessDeniedException("Доступ к счету запрещен!");
        }
        return account;
    }
}
