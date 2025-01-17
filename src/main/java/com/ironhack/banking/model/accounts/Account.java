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
    private BigDecimal maintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;
    private final Date creationDate = new Date();
    private Date chargedDate;

    public Account() {
    }

    public Account(@NotNull String balance, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal maintenanceFee) throws Exception {
        setBalance(balance);
        this.secretKey =  ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setMinimumBalance(minimumBalance);
        setMaintenanceFee(maintenanceFee);
        setStatus(Status.ACTIVE);
        this.chargedDate = null;
    }

    public Account(@NotNull  String balance, @NotNull AccountHolder primaryOwner, BigDecimal minimumBalance, BigDecimal maintenanceFee) throws Exception {
        setBalance(balance);
        this.secretKey =  ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setMinimumBalance(minimumBalance);
        setMaintenanceFee(maintenanceFee);
        setStatus(Status.ACTIVE);
        this.chargedDate = null;
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

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
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

    public Date getChargedDate() {
        return chargedDate;
    }

    public void setChargedDate(Date chargedDate) {
        this.chargedDate = chargedDate;
    }
}
