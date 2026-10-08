package ru.learn.javapracticehw.counterpart.dto;

import ru.learn.javapracticehw.common.enums.TypeCounterpart;

public record CounterpartResponseDto(
    String id,
    String name,
    String tin,
    TypeCounterpart type
) {
}
