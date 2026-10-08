package ru.learn.javapracticehw.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learn.javapracticehw.account.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAllByDepartmentId(UUID departmentId);
}
