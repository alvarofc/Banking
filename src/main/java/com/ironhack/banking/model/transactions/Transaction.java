package com.ironhack.banking.model.transactions;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.tools.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account orderingAccount;
    @ManyToOne
    private Account beneficiaryAccount;
    @Embedded
    private Money amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transaction() {
    }

    public Transaction(Account orderingAccount, Account beneficiaryAccount, String amount) {
        this.orderingAccount = orderingAccount;
        this.beneficiaryAccount = beneficiaryAccount;
        setAmount(amount);
        this.date =  new Date();
    }

    public Transaction(Account orderingAccount, String amount) {
        this.orderingAccount = orderingAccount;
        setAmount(amount);
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getOrderingAccount() {
        return orderingAccount;
    }

    public void setOrderingAccount(Account orderingAccount) {
        this.orderingAccount = orderingAccount;
    }

    public Account getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(Account beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = new Money(new BigDecimal(amount));;
    }

    public Date getDate() {
        return date;
    }


}
