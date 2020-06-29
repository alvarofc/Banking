package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.enums.Role;
import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.transactions.Transaction;
import com.ironhack.banking.model.users.User;
import com.ironhack.banking.repository.AccountRepository;
import com.ironhack.banking.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public Double maximumSpending(Integer id) {
        return transactionRepository.findMaximumSpending(id);
    }

    public List<Transaction> findAll() {
        LOGGER.info("Looked for all transactions");
        return transactionRepository.findAll();
    }
    public Transaction findById(Integer id) throws Exception {
        LOGGER.info("Looked for transaction" + id);
        return transactionRepository.findById(id).orElseThrow(()-> new Exception("Not transaction with that Id"));
    }


    public Transaction makeTransfer(Transaction transaction) throws Exception {
        Account senderAccount = accountService.findById(transaction.getOrderingAccount().getId());
        Account receiverAccount = accountService.findById(transaction.getBeneficiaryAccount().getId());
        Transaction trans = new Transaction(transaction.getOrderingAccount(), transaction.getBeneficiaryAccount(),transaction.getAmount().getAmount().toString());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User) principal);
        LOGGER.warn(principal);
        if (senderAccount.getBalance().getAmount().compareTo(transaction.getAmount().getAmount()) <0) {
            LOGGER.info("Not enough funds to transfer in account" + senderAccount.getId());
            throw new Exception("Not enough funds");
        } else if(transactionRepository.findAllGroupByOrderingAccount(senderAccount).size()>2 && new BigDecimal(transactionRepository.findMaximumSpending(senderAccount.getId()).toString()).multiply(new BigDecimal("1.5")).compareTo(transaction.getAmount().getAmount())<0) {
            senderAccount.setStatus(Status.FROZEN);
            accountService.save(senderAccount);
            LOGGER.info("Suspicious activity in account: " + senderAccount.getId());
            throw new Exception("Your account has been frozen due to irregular activities. Get in touch with your local branch.");
        }
        else if (senderAccount.getMinimumBalance() != null && senderAccount.getBalance().getAmount().subtract(transaction.getAmount().getAmount()).compareTo(senderAccount.getMinimumBalance())<0){
            senderAccount.getBalance().decreaseAmount(transaction.getAmount().increaseAmount(senderAccount.getPENALTYFEE()));
            receiverAccount.getBalance().increaseAmount(transaction.getAmount().getAmount());
            accountService.save(senderAccount);
            accountService.save(receiverAccount);
            transactionRepository.save(trans);
            LOGGER.info("Transaction done and funds below minimum for account: " + senderAccount.getId());
            return transaction;
        } else {
            senderAccount.getBalance().decreaseAmount(transaction.getAmount());
            receiverAccount.getBalance().increaseAmount(transaction.getAmount());
            accountService.save(senderAccount);
            accountService.save(receiverAccount);
            transactionRepository.save(trans);
            LOGGER.info("Transaction done for account: " + senderAccount.getId());
            return transaction;
        }

    }

    public Transaction withdraw(Integer id, String amount) throws Exception {
        Account account = accountService.findById(id);
        Money money = new Money(new BigDecimal(amount));
        Transaction trans = new Transaction(account, amount);
        if (account.getBalance().getAmount().compareTo(money.getAmount()) <0) {
            LOGGER.info("Not enough funds to withdraw in account" + id);
            throw new Exception("Not enough funds");
        } else if(transactionRepository.findAllGroupByOrderingAccount(account).size()>2 && new BigDecimal(transactionRepository.findMaximumSpending(account.getId()).toString()).multiply(new BigDecimal("1.5")).compareTo(money.getAmount())<0) {
            account.setStatus(Status.FROZEN);
            accountService.save(account);
            LOGGER.info("Suspicious activity in account: " +id);
            throw new Exception("Your account has been frozen due to irregular activities. Get in touch with your local branch.");
        }
        else if (account.getMinimumBalance() != null && account.getBalance().getAmount().subtract(money.getAmount()).compareTo(account.getMinimumBalance())<0){
            account.getBalance().decreaseAmount(money.getAmount().add(account.getPENALTYFEE()));
            accountService.save(account);
            transactionRepository.save(trans);
            LOGGER.info("Transaction done and funds below minimum for account: " + id);
            return trans;
        }else{
            account.getBalance().decreaseAmount(new BigDecimal(amount));
            accountService.save(account);

            transactionRepository.save(trans);
            LOGGER.info("Withdrawal successful for account: " + id);
            return trans;
        }



    }
    public Transaction deposit(Integer id, String amount) throws Exception {
        Account account = accountService.findById(id);
        Transaction trans = new Transaction(account, amount);
        account.getBalance().increaseAmount(new BigDecimal(amount));
        accountService.save(account);
        LOGGER.info("Successful deposit for account: " + id);
        return transactionRepository.save(trans);

    }


}
