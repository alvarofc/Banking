package com.ironhack.banking.model.accounts;

import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


@DiscriminatorValue(value="checking")
@Entity
public class Checking extends Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Checking() {
    }


    public Checking(@NotNull String balance, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner) throws Exception {
        super(balance, primaryOwner, secondaryOwner, new BigDecimal("250"),  new BigDecimal("12"));
    }

    public Checking(@NotNull @PositiveOrZero String balance, @NotNull AccountHolder primaryOwner) throws Exception {
        super(balance, primaryOwner, new BigDecimal("250"), new BigDecimal("12"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
