package ru.learn.javapracticehw.audit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.learn.javapracticehw.audit.dto.AuditResponseDto;
import ru.learn.javapracticehw.audit.entity.AuditLog;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuditMapper {
    AuditResponseDto toAuditResponseDto(AuditLog auditLog);
}
