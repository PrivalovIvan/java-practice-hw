package ru.learn.javapracticehw.audit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.learn.javapracticehw.audit.dto.AuditResponseDto;
import ru.learn.javapracticehw.audit.service.AuditService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditServiceImpl;

    @GetMapping("/audits")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<Page<AuditResponseDto>> getAllAudits(Pageable pageable) {
        return ResponseEntity.ok(auditServiceImpl.getAllAudits(pageable));
    }
}
