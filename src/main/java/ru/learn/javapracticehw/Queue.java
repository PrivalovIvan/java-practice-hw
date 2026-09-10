package ru.learn.javapracticehw;

public interface Queue<T> {
    void enqueue(T t);

    T dequeue();

    int size();
}
