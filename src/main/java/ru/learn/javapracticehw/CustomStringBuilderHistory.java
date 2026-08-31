package ru.learn.javapracticehw;

import java.util.Stack;

public class CustomStringBuilderHistory {
    private final Stack<CustomStringBuilder.CustomStringBuilderSnapshot> history = new Stack<>();
    private final CustomStringBuilder csb;

    public CustomStringBuilderHistory(CustomStringBuilder csb) {
        this.csb = csb;
    }

    public void backup() {
        history.push(csb.takeSnapshot());
    }

    public void undo() {
        if (!history.empty()) {
            csb.restore(history.pop());
        }
    }
}
