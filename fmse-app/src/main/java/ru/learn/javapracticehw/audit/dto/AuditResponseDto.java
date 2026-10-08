package ru.learn.javapracticehw.audit.dto;

import ru.learn.javapracticehw.audit.entity.AuditLog;

import java.time.Instant;
import java.util.UUID;

public record AuditResponseDto(
    UUID id,
    UUID transactionId,
    AuditLog.Action action,
    String authorEmail,
    Instant timestamp
) {
}
