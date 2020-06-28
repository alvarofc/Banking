package com.ironhack.banking.model.accounts;

import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value="student")
public class StudentChecking extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public StudentChecking() {
    }

    public StudentChecking(String balance, AccountHolder primaryOwner) throws Exception {
        super(balance, primaryOwner, null,  null);
    }

    public StudentChecking(String balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) throws Exception {
        super(balance, primaryOwner, secondaryOwner, null,  null);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

