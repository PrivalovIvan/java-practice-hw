package ru.learn.javapracticehw.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learn.javapracticehw.category.entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
