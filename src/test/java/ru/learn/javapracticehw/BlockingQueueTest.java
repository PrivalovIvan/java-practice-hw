package ru.learn.javapracticehw;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockingQueueTest {

    @Test
    void concurrentEnqueueAndDequeue_shouldNotLoseData() throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(50);
        var pool = Executors.newFixedThreadPool(10);
        var results = new CopyOnWriteArrayList<Integer>();

        CountDownLatch latch = new CountDownLatch(100);

        try (pool) {
            for (int i = 0; i < 100; i++) {
                final int elem = i;
                pool.submit(() -> queue.enqueue(elem));

                pool.submit(() -> {
                    results.add(queue.dequeue());
                    latch.countDown();
                });
            }
            boolean completed = latch.await(5, TimeUnit.SECONDS);
            pool.shutdown();

            assertTrue(completed);
            assertEquals(100, results.size());
        }
    }

    @Test
    void dequeueOnEmptyQueue_shouldBlockThread() throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        var consumer = new Thread(queue::dequeue);
        consumer.start();

        Thread.sleep(300);

        assertEquals(Thread.State.WAITING, consumer.getState());

        consumer.interrupt();
    }
}
