package ru.learn.javapracticehw;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyAnalysis {
    public static <T> Map<T, Long> analysis(T[] array) {
        if (array == null) {
            return Collections.emptyMap();
        }

        return Arrays.stream(array)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
