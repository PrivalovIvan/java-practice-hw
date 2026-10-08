package ru.learn.javapracticehw.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.TypeAccount;
import ru.learn.javapracticehw.department.entity.Department;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Enumerated(EnumType.STRING)
    private TypeAccount type;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance = BigDecimal.ZERO;

    public Account(Department department, TypeAccount type, Currency currency) {
        this.department = department;
        this.type = type;
        this.currency = currency;
    }

    public void addFunds(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdrawFunds(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
