package com.ironhack.banking.model.accounts;



import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Money balance;
    @ManyToOne(fetch = FetchType.EAGER)
    private AccountHolder primaryOwner;
    @ManyToOne(fetch = FetchType.EAGER)
    private AccountHolder secondaryOwner;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal penaltyFee;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date creationDate = new Date();

    public CreditCard() {
    }

    public CreditCard(String balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) throws Exception {
        setBalance(balance);
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creditLimit = new BigDecimal("100");
        this.interestRate = new BigDecimal("0.2");
        this.penaltyFee = new BigDecimal("40");
    }

    public CreditCard(String balance, AccountHolder primaryOwner) throws Exception {
        setBalance(balance);
        this.primaryOwner = primaryOwner;
        this.creditLimit = new BigDecimal("100");
        this.interestRate = new BigDecimal("0.2");
        this.penaltyFee = new BigDecimal("40");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(String balance) throws Exception {
        Money bal = new Money(new BigDecimal(balance));
        if (bal.getAmount().compareTo(BigDecimal.ZERO)< 0) throw new Exception("Negative values not allowed");
        this.balance = bal;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) throws Exception {
        if (creditLimit.compareTo(new BigDecimal("100000"))>0){
            throw new Exception("Values higher than 100000 not allowed");
        }else if (creditLimit.compareTo(BigDecimal.ZERO)<0){
            throw new Exception("Values lower than 0 not allowed");
        }
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws Exception {
        if (interestRate.compareTo(new BigDecimal("0.1"))<0){
            throw new Exception("Values lower than 0.1 not allowed");
        }
        this.interestRate = interestRate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) throws Exception {
        if (penaltyFee.compareTo(BigDecimal.ZERO)<0){
            throw new Exception("Values lower than 0 not allowed");
        }
        this.penaltyFee = penaltyFee;
    }
}
