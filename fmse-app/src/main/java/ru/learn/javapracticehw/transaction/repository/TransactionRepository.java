package ru.learn.javapracticehw.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.learn.javapracticehw.report.dto.CategoryFlowDto;
import ru.learn.javapracticehw.transaction.entity.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("""
            select new ru.learn.javapracticehw.report.dto.CategoryFlowDto(
            c.name, t.type, SUM(t.amount)
            )
            from Transaction t
            left join t.category c
            where (t.fromAccount.department.id = :departmentId or t.toAccount.department.id = :departmentId)
            and t.status = 'CONFIRMED'
            and t.date >= :startDate and t.date <= :endDate
            group by c.name, t.type
        """)
    List<CategoryFlowDto> getCashFlowByCategory(
        @Param("departmentId") UUID departmentId,
        @Param("startDate") Instant startDate,
        @Param("endDate") Instant endDate
    );
}
