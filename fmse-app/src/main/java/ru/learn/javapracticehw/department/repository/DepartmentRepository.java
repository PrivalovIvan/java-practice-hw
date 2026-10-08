package ru.learn.javapracticehw.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learn.javapracticehw.department.entity.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
