package com.corebank.repo;

import com.corebank.model.BillPayment;
import com.corebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillPaymentRepository extends JpaRepository<BillPayment, Long> {
    @Query("select bp from BillPayment bp where bp.sourceAccount.owner = ?1")
    List<BillPayment> findByOwner(User owner);
}
