package com.ironhack.banking.model.accounts;

import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
@Entity
@DiscriminatorColumn(name="type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Embedded
    private Money balance;
    @NotNull
    private int secretKey;
    @NotNull
    @ManyToOne
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;
    private BigDecimal minimumBalance;
    private final BigDecimal PENALTYFEE = new BigDecimal("40");
    private BigDecimal monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
    private final Date creationDate = new Date();

    public Account() {
    }

    public Account(@NotNull String balance, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) throws Exception {
        setBalance(balance);
        this.secretKey =  ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setStatus(Status.ACTIVE);
    }

    public Account(@NotNull  String balance, @NotNull AccountHolder primaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) throws Exception {
        setBalance(balance);
        this.secretKey =  ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setStatus(Status.ACTIVE);
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(String balance) throws Exception {
        this.balance = new Money(new BigDecimal(balance));
    }

    public int getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(int secretKey) {
        this.secretKey = secretKey;
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

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getPENALTYFEE() {
        return PENALTYFEE;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
