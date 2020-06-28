
package com.ironhack.banking.model.accounts;

import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DiscriminatorValue(value="savings")
public class Savings extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal interestRate;


    public Savings() {
    }

    public Savings(AccountHolder primaryOwner, AccountHolder secondaryOwner, String balance) throws Exception {
        super(balance, primaryOwner, secondaryOwner, new BigDecimal("1000"),  null);
        setInterestRate(new BigDecimal("0.0025"));
    }

    public Savings(AccountHolder primaryOwner, String balance) throws Exception {
        super(balance, primaryOwner, new BigDecimal("1000"), null);
        setInterestRate(new BigDecimal("0.0025"));
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws Exception{
        if (interestRate.compareTo(new BigDecimal("0.5")) > 0){
            throw new Exception("No value bigger than 0.5 allowed");
        }else if (interestRate.compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("No value lower than 0 allowed");
        }
        this.interestRate = interestRate;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public void setBalance(String balance) throws Exception {
        Money bal = new Money(new BigDecimal(balance));
        if (bal.getAmount().compareTo(new BigDecimal("100"))<0){
            throw new Exception("Values less than 100 not allowed");
        }
        super.setBalance(balance);
    }

    @Override
    public String toString() {
        return "Savings{" +
                "interestRate=" + interestRate +
                '}';
    }
}
