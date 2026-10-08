package ru.learn.javapracticehw.counterpart.service;

import ru.learn.javapracticehw.counterpart.entity.Counterpart;

import java.util.UUID;

public interface CounterpartService {
    Counterpart getCounterpartEntityById(UUID counterpartId);
}
