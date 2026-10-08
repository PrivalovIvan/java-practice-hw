package ru.learn.javapracticehw.audit.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.learn.javapracticehw.audit.dto.AuditResponseDto;
import ru.learn.javapracticehw.audit.mapper.AuditMapper;
import ru.learn.javapracticehw.audit.repository.AuditLogsRepository;
import ru.learn.javapracticehw.audit.service.AuditService;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogsRepository auditLogsRepository;
    private final AuditMapper auditMapper;

    @Override
    public Page<AuditResponseDto> getAllAudits(Pageable pageable) {
        return auditLogsRepository.findAll(pageable)
            .map(auditMapper::toAuditResponseDto);
    }
}
