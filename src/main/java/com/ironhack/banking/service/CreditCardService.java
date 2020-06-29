package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return creditCardRepository.findById(id).orElseThrow(()-> new Exception("No Credit Card with that id"));
    }

}
