package com.corebank.repo;

import com.corebank.model.Payee;
import com.corebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
    List<Payee> findByOwner(User owner);
}
