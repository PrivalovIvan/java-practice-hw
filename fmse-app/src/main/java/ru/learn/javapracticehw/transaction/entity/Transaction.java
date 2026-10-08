package ru.learn.javapracticehw.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.learn.javapracticehw.account.entity.Account;
import ru.learn.javapracticehw.category.entity.Category;
import ru.learn.javapracticehw.common.enums.Currency;
import ru.learn.javapracticehw.common.enums.StatusTransaction;
import ru.learn.javapracticehw.common.enums.TypeTransaction;
import ru.learn.javapracticehw.counterpart.entity.Counterpart;
import ru.learn.javapracticehw.user.entity.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Instant date = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counterpart_id")
    private Counterpart counterpart;

    @Enumerated(EnumType.STRING)
    private StatusTransaction status = StatusTransaction.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_transaction_id")
    private Transaction originalTransaction;

    public Transaction(TypeTransaction type,
                       BigDecimal amount,
                       Currency currency,
                       Account fromAccount,
                       Account toAccount,
                       Category category,
                       Counterpart counterpart,
                       User author
    ) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.category = category;
        this.counterpart = counterpart;
        this.author = author;
    }

    public Transaction confirm() {
        if (this.status == StatusTransaction.DRAFT) {
            this.status = StatusTransaction.CONFIRMED;
        }
        return this;
    }

    public Transaction createStorno(User author) {
        var stornoTransaction = new Transaction(
            this.type,
            this.amount.negate(),
            this.currency,
            this.fromAccount,
            this.toAccount,
            this.category,
            this.counterpart,
            author

        );

        stornoTransaction.status = StatusTransaction.CONFIRMED;
        stornoTransaction.originalTransaction = this;

        this.status = StatusTransaction.CANCELLED;

        return stornoTransaction;
    }
}
