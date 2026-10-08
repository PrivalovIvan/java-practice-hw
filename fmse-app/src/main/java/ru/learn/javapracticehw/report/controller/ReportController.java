package ru.learn.javapracticehw.report.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.report.dto.CashFlowReportDto;
import ru.learn.javapracticehw.report.service.ReportService;
import ru.learn.javapracticehw.security.model.SecurityUser;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/cash/flow")
    @PreAuthorize("hasAnyRole('FINANCE_MANAGER', 'ADMIN', 'AUDITOR')")
    public ResponseEntity<CashFlowReportDto> getCashFlow(
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate,
        @AuthenticationPrincipal SecurityUser securityUser
    ) {
        return ResponseEntity
            .ok(reportService.getCashFlowReport(startDate, endDate, securityUser.user()));
    }
}
