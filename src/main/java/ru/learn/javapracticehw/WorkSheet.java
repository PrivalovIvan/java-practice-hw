package ru.learn.javapracticehw;

import java.util.List;

public class WorkSheet {
    String name;
    List<DayOfWeek> workDays;

    public WorkSheet() {
    }

    public WorkSheet(String name, List<DayOfWeek> workDays) {
        this.name = name;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public List<DayOfWeek> getWorkDays() {
        return workDays;
    }
}
