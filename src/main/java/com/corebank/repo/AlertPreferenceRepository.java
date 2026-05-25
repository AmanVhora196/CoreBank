package com.corebank.repo;

import com.corebank.model.AlertPreference;
import com.corebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertPreferenceRepository extends JpaRepository<AlertPreference, Long> {
    Optional<AlertPreference> findByUser(User user);
}
