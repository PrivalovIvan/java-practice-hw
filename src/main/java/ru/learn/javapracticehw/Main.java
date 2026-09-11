package ru.learn.javapracticehw;

import ru.learn.javapracticehw.service.impl.MockFetchServiceImpl;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var poolVirtual = Executors.newVirtualThreadPerTaskExecutor();

        try (poolVirtual) {
            DataAggregator dataAggregator = new DataAggregator(new MockFetchServiceImpl(), poolVirtual);

            dataAggregator.aggregateProductInfoAsync("Ноутбук")
                .thenAccept(productInfo -> System.out.println("aggregateProductInfoAsync: " + productInfo));

            var notebook = dataAggregator.aggregateProductInfo("Ноутбук");
            System.out.println("aggregateProductInfo: " + notebook);

            Thread.sleep(3000);
        }
    }
}
