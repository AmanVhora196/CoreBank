package com.corebank.repo;

import com.corebank.model.Budget;
import com.corebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByOwner(User owner);
}
