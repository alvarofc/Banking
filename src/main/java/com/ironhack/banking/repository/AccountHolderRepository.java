package com.ironhack.banking.repository;

import com.ironhack.banking.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
}
