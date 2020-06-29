package com.ironhack.banking.repository;

import com.ironhack.banking.model.transactions.CreditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
@Repository
public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, Integer> {


}
