package ru.learn.javapracticehw.service.impl;

import ru.learn.javapracticehw.service.FetchService;

import java.util.concurrent.ThreadLocalRandom;

public class MockFetchServiceImpl implements FetchService {
    private static final int START_CHANCE_PERCENT = 1;
    private static final int FINISH_CHANCE_PERCENT = 100;
    private static final int CHANCE_PERCENT_THROW_EXCEPTION = 20;
    private static final int MIN_TIME_SLEEP_SECONDS = 1;
    private static final int MAX_TIME_SLEEP_SECONDS = 3;

    @Override
    public double fetchPrice() {
        return executeWithSimulation(899.99);
    }

    @Override
    public String fetchDescription() {
        return executeWithSimulation("Самое интересное описание ноутбука");
    }

    @Override
    public double fetchRating() {
        return executeWithSimulation(4.7);
    }

    private <T> T executeWithSimulation(T resultValue) {
        mockProcessFetch();

        if (throwException()) {
            throw new RuntimeException("Ошибка при получении данных");
        }

        return resultValue;
    }

    private void mockProcessFetch() {
        try {
            Thread.sleep(randomSleepTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Поток был принудительно прерван во время ожидания", e);
        }
    }

    private long randomSleepTime() {
        return ThreadLocalRandom.current()
                   .nextLong(MIN_TIME_SLEEP_SECONDS, MAX_TIME_SLEEP_SECONDS + 1) * 1000;
    }

    private boolean throwException() {
        return ThreadLocalRandom.current()
                   .nextLong(START_CHANCE_PERCENT, FINISH_CHANCE_PERCENT + 1) <= CHANCE_PERCENT_THROW_EXCEPTION;
    }
}
