package ru.learn.javapracticehw;

import java.util.Arrays;
import java.util.Objects;

public class CustomStringBuilder {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int BUFFER_CAPACITY = 16;
    private char[] value;
    private int count;

    public CustomStringBuilder() {
        this.value = new char[DEFAULT_CAPACITY];
    }

    public CustomStringBuilder(String str) {
        this.count = str.length();
        this.value = new char[this.count + BUFFER_CAPACITY];
        str.getChars(0, str.length(), this.value, 0);
    }

    public CustomStringBuilder append(String str) {
        if (Objects.isNull(str)) {
            str = "null";
        }

        int len = str.length();
        ensureCapacity(this.count + len);
        str.getChars(0, len, this.value, this.count);
        this.count += len;

        return this;
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > this.value.length) {
            int newCapacity = this.value.length * 2 + 2;

            if (newCapacity < minimumCapacity) {
                newCapacity = minimumCapacity;
            }

            this.value = Arrays.copyOf(this.value, newCapacity);
        }
    }

    @Override
    public String toString() {
        return new String(this.value, 0, this.count);
    }

    public CustomStringBuilderSnapshot takeSnapshot() {
        char[] valueCopy = Arrays.copyOf(this.value, this.value.length);
        return new CustomStringBuilderSnapshot(valueCopy, this.count);
    }

    public void restore(CustomStringBuilderSnapshot snapshot) {
        this.value = snapshot.value;
        this.count = snapshot.count;
    }

    public record CustomStringBuilderSnapshot(char[] value, int count) {
    }
}
