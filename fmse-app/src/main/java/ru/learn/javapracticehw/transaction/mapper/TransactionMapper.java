package ru.learn.javapracticehw.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.transaction.dto.TransactionResponseDto;
import ru.learn.javapracticehw.transaction.entity.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    TransactionResponseDto toTransactionResponseDto(Transaction transaction);
}
