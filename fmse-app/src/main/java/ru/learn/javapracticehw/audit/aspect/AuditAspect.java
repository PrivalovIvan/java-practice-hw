package ru.learn.javapracticehw.audit.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.learn.javapracticehw.audit.entity.AuditLog;
import ru.learn.javapracticehw.audit.repository.AuditLogsRepository;
import ru.learn.javapracticehw.transaction.dto.TransactionResponseDto;
import ru.learn.javapracticehw.user.entity.User;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditLogsRepository logsRepository;

    @AfterReturning(pointcut = "@annotation(auditable)", returning = "result")
    public void audit(JoinPoint joinPoint, Auditable auditable, TransactionResponseDto result) {
        User currentUser = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof User user) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null || result == null) {
            return;
        }

        AuditLog auditLog = AuditLog.builder()
            .transactionId(result.id())
            .action(auditable.action())
            .authorEmail(currentUser.getEmail())
            .build();

        logsRepository.save(auditLog);
    }
}

