package ru.learn.javapracticehw.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.learn.javapracticehw.budget.entity.Budget;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
    @Query("""
        select sum(t.amount) from Transaction t
        where t.fromAccount.department.id = :departmentId
        and t.category.id = :categoryId
        and t.date >= :startDate and t.date <= :endDate
        and t.status = 'CONFIRMED'
        and t.type = 'EXPENSE'
        """)
    BigDecimal calculateExpense(
        @Param("departmentId") UUID departmentId,
        @Param("categoryId") UUID categoryId,
        @Param("startDate") Instant startDate,
        @Param("endDate") Instant endDate
    );
}
