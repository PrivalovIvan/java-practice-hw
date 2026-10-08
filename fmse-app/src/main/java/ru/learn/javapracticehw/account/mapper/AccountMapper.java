package ru.learn.javapracticehw.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.account.dto.AccountResponseDto;
import ru.learn.javapracticehw.account.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    AccountResponseDto toResponseDto(Account account);
}
