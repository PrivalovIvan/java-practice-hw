package ru.learn.javapracticehw.budget.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.learn.javapracticehw.category.entity.Category;
import ru.learn.javapracticehw.department.entity.Department;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDate startDate;
    private LocalDate endDate;
}
