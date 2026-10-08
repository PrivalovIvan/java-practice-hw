package ru.learn.javapracticehw.report.service;

import ru.learn.javapracticehw.report.dto.CashFlowReportDto;
import ru.learn.javapracticehw.user.entity.User;

import java.time.LocalDate;

public interface ReportService {
    CashFlowReportDto getCashFlowReport(LocalDate startDate, LocalDate endDate, User currentUser);
}
