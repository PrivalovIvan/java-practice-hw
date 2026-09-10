package ru.learn.javapracticehw;

import java.util.ArrayDeque;

public class BlockingQueue<T> implements Queue<T> {
    private final ArrayDeque<T> queue;
    private final Object lock = new Object();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = new ArrayDeque<>(capacity);
    }

    @Override
    public void enqueue(T t) {
        synchronized (lock) {
            while (queue.size() == capacity) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            queue.add(t);
            lock.notifyAll();
        }
    }

    @Override
    public T dequeue() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            T item = queue.poll();
            lock.notifyAll();
            return item;
        }
    }

    @Override
    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }
}
