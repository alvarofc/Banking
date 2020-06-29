package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.model.transactions.CreditTransaction;
import com.ironhack.banking.repository.CreditTransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditTransactionService {
    @Autowired
    CreditTransactionRepository repository;
    @Autowired
    CreditCardService creditCardService;
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public CreditTransaction withdraw(Integer id, String amount) throws Exception {
        CreditCard card = creditCardService.findById(id);
        if(card.getBalance().increaseAmount(new BigDecimal(amount)).compareTo(card.getCreditLimit())>0)throw new Exception("Not enough credit");
        card.getBalance().increaseAmount(new BigDecimal(amount));
        CreditTransaction trans = new CreditTransaction(card, amount);
        creditCardService.save(card);
        LOGGER.info("New credit withdrawal for card: " + card.getId());
        return repository.save(trans);
    }
    public CreditTransaction deposit(Integer id, String amount) throws Exception {
        CreditCard card = creditCardService.findById(id);
        card.getBalance().decreaseAmount(new BigDecimal(amount));
        CreditTransaction trans = new CreditTransaction(card, amount);
        creditCardService.save(card);
        LOGGER.info("New credit deposit for card: " + card.getId());
        return repository.save(trans);
    }
}
