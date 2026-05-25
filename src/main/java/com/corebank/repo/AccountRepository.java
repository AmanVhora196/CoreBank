package com.corebank.repo;

import com.corebank.model.Account;
import com.corebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner(User owner);
    Optional<Account> findByIdAndOwner(Long id, User owner);
}
