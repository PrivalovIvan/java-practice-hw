package ru.learn.javapracticehw.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_logs")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;

    @Column(name = "author_email", nullable = false)
    private String authorEmail;

    @CreationTimestamp
    private Instant timestamp;

    public AuditLog(UUID transactionId, Action action, String authorEmail, Instant timestamp) {
        this.transactionId = transactionId;
        this.action = action;
        this.authorEmail = authorEmail;
        this.timestamp = timestamp;
    }

    public enum Action {
        CREATION,
        CONFIRMATION,
        STORN
    }
}
