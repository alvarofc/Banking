package com.ironhack.banking.model.transactions;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.model.tools.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class CreditTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private CreditCard creditCard;
    @Embedded
    private Money amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public CreditTransaction() {
    }



    public CreditTransaction(CreditCard creditCard, String amount) {
        this.creditCard = creditCard;
        setAmount(amount);
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CreditCard getOrderingAccount() {
        return creditCard;
    }

    public void setOrderingAccount(CreditCard creditCard) {
        this.creditCard = creditCard;
    }


    public Money getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = new Money(new BigDecimal(amount));
    }

    public Date getDate() {
        return date;
    }


}
