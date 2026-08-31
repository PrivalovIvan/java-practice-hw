package ru.learn.javapracticehw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomStringBuilderTest {

    @Test
    void shouldAppendStringCorrectly() {
        CustomStringBuilder csb = new CustomStringBuilder("Hello,");
        csb.append(" World!");
        assertEquals("Hello, World!", csb.toString());
    }

    @Test
    void shouldHandleNullValues() {
        CustomStringBuilder csb = new CustomStringBuilder();
        csb.append(null);
        assertEquals("null", csb.toString());
    }

    @Test
    void shouldRestoreStateFromHistory() {
        CustomStringBuilder csb = new CustomStringBuilder("A");
        CustomStringBuilderHistory history = new CustomStringBuilderHistory(csb);
        history.backup();

        csb.append("B");
        history.backup();
        csb.append("C");

        history.undo();
        assertEquals("AB", csb.toString());
        history.undo();
        assertEquals("A", csb.toString());
    }
}
