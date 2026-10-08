package ru.learn.javapracticehw.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learn.javapracticehw.audit.entity.AuditLog;

import java.util.UUID;

public interface AuditLogsRepository extends JpaRepository<AuditLog, UUID> {
}
