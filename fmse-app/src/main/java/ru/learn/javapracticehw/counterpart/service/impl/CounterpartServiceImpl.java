package ru.learn.javapracticehw.counterpart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.counterpart.entity.Counterpart;
import ru.learn.javapracticehw.counterpart.exception.CounterpartNotFoundRepository;
import ru.learn.javapracticehw.counterpart.repository.CounterpartRepository;
import ru.learn.javapracticehw.counterpart.service.CounterpartService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounterpartServiceImpl implements CounterpartService {
    private final CounterpartRepository counterpartRepository;

    @Override
    public Counterpart getCounterpartEntityById(UUID counterpartId) {
        return counterpartRepository.findById(counterpartId)
            .orElseThrow(() -> new CounterpartNotFoundRepository("Контрагент с id: %s не найден".formatted(counterpartId)));
    }
}
