package ru.learn.javapracticehw.counterpart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.counterpart.dto.CounterpartResponseDto;
import ru.learn.javapracticehw.counterpart.entity.Counterpart;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CounterpartMapper {

    CounterpartResponseDto toCounterpartResponseDto(Counterpart counterpart);
}
