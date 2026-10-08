package ru.learn.javapracticehw.counterpart.dto;

import ru.learn.javapracticehw.common.enums.TypeCounterpart;

public record CounterpartCreatedDto(
    String name,
    String tin,
    TypeCounterpart type
) {
}
