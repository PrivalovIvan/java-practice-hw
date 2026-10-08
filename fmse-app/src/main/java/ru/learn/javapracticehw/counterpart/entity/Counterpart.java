package ru.learn.javapracticehw.counterpart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.learn.javapracticehw.common.enums.TypeCounterpart;

import java.util.UUID;

@Entity
@Table(name = "counterparties")
@Getter
@NoArgsConstructor
public class Counterpart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String tin; // инн

    @Enumerated(EnumType.STRING)
    private TypeCounterpart type;
}
