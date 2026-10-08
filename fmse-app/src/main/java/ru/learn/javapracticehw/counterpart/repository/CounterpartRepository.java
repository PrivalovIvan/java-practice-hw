package ru.learn.javapracticehw.counterpart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learn.javapracticehw.counterpart.entity.Counterpart;

import java.util.UUID;

public interface CounterpartRepository extends JpaRepository<Counterpart, UUID> {
}
