package ru.learn.javapracticehw;

import ru.learn.javapracticehw.service.FetchService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class DataAggregator {
    private static final int TIMEOUT_SECONDS = 3;
    private final FetchService fetchService;
    private final ExecutorService poolVirtual;

    public DataAggregator(FetchService fetchService, ExecutorService poolVirtual) {
        this.fetchService = fetchService;
        this.poolVirtual = poolVirtual;
    }

    // Метод по задаче. Запускает три параллельные задачи, но блокирует главный поток, что противоречит заданию.
    ProductInfo aggregateProductInfo(String productName) {
        return aggregateProductInfoAsync(productName).join();
    }

    // Метод не блокирует главный поток, как требовалось по заданию.
    CompletableFuture<ProductInfo> aggregateProductInfoAsync(String productName) {
        var descriptionTask = fetchAsync(fetchService::fetchDescription, "Нет данных");
        var priceTask = fetchAsync(fetchService::fetchPrice, 0.0);
        var ratingTask = fetchAsync(fetchService::fetchRating, 0.0);

        return CompletableFuture.allOf(descriptionTask, priceTask, ratingTask)
            .thenApply(v -> new ProductInfo(
                productName,
                priceTask.join(),
                descriptionTask.join(),
                ratingTask.join())
            );
    }

    private <T> CompletableFuture<T> fetchAsync(Supplier<T> supplier, T fallbackValue) {
        return CompletableFuture.supplyAsync(supplier, poolVirtual)
            .completeOnTimeout(fallbackValue, TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .exceptionally(ex -> fallbackValue);
    }
}
