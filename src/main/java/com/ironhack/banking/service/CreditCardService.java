package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard saveNew(CreditCard creditCard) throws Exception {
        CreditCard newCred = new CreditCard(creditCard.getBalance().getAmount().toString(), creditCard.getPrimaryOwner(), creditCard.getSecondaryOwner());
        return creditCardRepository.save(newCred);
    }

    public CreditCard save(CreditCard creditCard) throws Exception {
        return creditCardRepository.save(creditCard);
    }

    public CreditCard findById(Integer id) throws Exception {
        CreditCard result = creditCardRepository.findById(id).orElseThrow(() -> new Exception("No Credit Card with that id"));
        if (result.getChargedDate() == null) {
            LocalDate currentDay = LocalDate.now();
            Date creation = new Date(result.getCreationDate().getTime());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localCreation = creation.toInstant().atZone(defaultZoneId).toLocalDate();
            Period p = Period.between(localCreation, currentDay);
            if (p.getMonths() > 1) {
                result.getBalance().decreaseAmount(result.getInterestRate().divide(new BigDecimal("12").add(new BigDecimal("1")).multiply(result.getBalance().getAmount())));
                result.setChargedDate(new Date());
                creditCardRepository.save(result);
            }
        } else {
            LocalDate currentDay = LocalDate.now();
            Date charged = new Date(result.getChargedDate().getTime());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localCharged = charged.toInstant().atZone(defaultZoneId).toLocalDate();
            Period p = Period.between(localCharged, currentDay);
            if (p.getYears() > 1) {
                result.getBalance().decreaseAmount(result.getInterestRate().divide(new BigDecimal("12").add(new BigDecimal("1")).multiply(result.getBalance().getAmount())));
                result.setChargedDate(new Date());
                creditCardRepository.save(result);
            }
        }
        return result;
    }

}
