package com.ironhack.banking.repository;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {



    @Query(value = "SELECT MAX(QTY) from  (SELECT SUM(amount) as QTY from transaction WHERE ordering_account_id = :id GROUP BY DAY(date))a", nativeQuery = true)
    public Double findMaximumSpending(@Param(value = "id") Integer id);

    public List<Transaction> findAllGroupByOrderingAccount(Account account);
}
