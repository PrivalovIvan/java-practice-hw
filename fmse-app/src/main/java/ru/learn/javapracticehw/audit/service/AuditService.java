package ru.learn.javapracticehw.audit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.learn.javapracticehw.audit.dto.AuditResponseDto;

public interface AuditService {
    Page<AuditResponseDto> getAllAudits(Pageable pageable);
}
