package ru.learn.javapracticehw;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyAnalysisTest {

    @Test
    void shouldReturnMapForStrings() {
        var array = new String[]{"A", "A", "B"};

        Map<String, Long> result = FrequencyAnalysis.analysis(array);

        assertEquals(Map.of("A", 2L, "B", 1L), result);
    }

    @Test
    void shouldReturnFrequencyMapForIntegers() {
        var array = new Integer[]{1, 1, 2, 3, 3, 3};

        Map<Integer, Long> result = FrequencyAnalysis.analysis(array);

        assertEquals(Map.of(1, 2L, 2, 1L, 3, 3L), result);
    }

    @Test
    void shouldReturnEmptyMapForEmptyArray() {
        var array = new String[0];

        Map<String, Long> result = FrequencyAnalysis.analysis(array);

        assertEquals(Map.of(), result);
    }
}
