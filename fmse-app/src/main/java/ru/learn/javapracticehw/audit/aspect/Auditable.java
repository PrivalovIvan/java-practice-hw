package ru.learn.javapracticehw.audit.aspect;

import ru.learn.javapracticehw.audit.entity.AuditLog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auditable {
    AuditLog.Action action();
}
