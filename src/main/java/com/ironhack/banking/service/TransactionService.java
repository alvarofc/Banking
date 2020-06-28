package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.enums.Status;
import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.transactions.Transaction;
import com.ironhack.banking.repository.AccountRepository;
import com.ironhack.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Double maximumSpending(Integer id) {
        return transactionRepository.findMaximumSpending(id);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    public Transaction findById(Integer id) throws Exception {
        return transactionRepository.findById(id).orElseThrow(()-> new Exception("Not transaction with that Id"));
    }


    public Transaction makeTransfer(Transaction transaction) throws Exception {
        Account senderAccount = accountService.findById(transaction.getOrderingAccount().getId());
        Account receiverAccount = accountService.findById(transaction.getBeneficiaryAccount().getId());
        Transaction trans = new Transaction(transaction.getOrderingAccount(), transaction.getBeneficiaryAccount(),transaction.getAmount().getAmount().toString());

        if (senderAccount.getBalance().getAmount().compareTo(transaction.getAmount().getAmount()) <0) {
            throw new Exception("Not enough funds");
        } else if(transactionRepository.findAllGroupByOrderingAccount(senderAccount).size()>2 && new BigDecimal(transactionRepository.findMaximumSpending(senderAccount.getId()).toString()).multiply(new BigDecimal("1.5")).compareTo(transaction.getAmount().getAmount())<0) {
            senderAccount.setStatus(Status.FROZEN);
            accountService.save(senderAccount);
            throw new Exception("Your account has been frozen due to irregular activities. Get in touch with your local branch.");
        }
        else if (senderAccount.getMinimumBalance() != null && senderAccount.getBalance().getAmount().subtract(transaction.getAmount().getAmount()).compareTo(senderAccount.getMinimumBalance())<0){
            senderAccount.getBalance().decreaseAmount(transaction.getAmount().increaseAmount(senderAccount.getPENALTYFEE()));
            receiverAccount.getBalance().increaseAmount(transaction.getAmount().getAmount());
            accountService.save(senderAccount);
            accountService.save(receiverAccount);
            transactionRepository.save(trans);
            return transaction;
        } else {
            senderAccount.getBalance().decreaseAmount(transaction.getAmount());
            receiverAccount.getBalance().increaseAmount(transaction.getAmount());
            accountService.save(senderAccount);
            accountService.save(receiverAccount);
            transactionRepository.save(trans);
            return transaction;
        }

    }

    public Transaction withdraw(Integer id, String amount) throws Exception {
        Account account = accountService.findById(id);
        Money money = new Money(new BigDecimal(amount));
        Transaction trans = new Transaction(account, amount);
        if (account.getBalance().getAmount().compareTo(money.getAmount()) <0) {
            throw new Exception("Not enough funds");
        } else if(transactionRepository.findAllGroupByOrderingAccount(account).size()>2 && new BigDecimal(transactionRepository.findMaximumSpending(account.getId()).toString()).multiply(new BigDecimal("1.5")).compareTo(money.getAmount())<0) {
            account.setStatus(Status.FROZEN);
            accountService.save(account);
            throw new Exception("Your account has been frozen due to irregular activities. Get in touch with your local branch.");
        }
        else if (account.getMinimumBalance() != null && account.getBalance().getAmount().subtract(money.getAmount()).compareTo(account.getMinimumBalance())<0){
            account.getBalance().decreaseAmount(money.getAmount().add(account.getPENALTYFEE()));
            accountService.save(account);
            transactionRepository.save(trans);
            return trans;
        }else{
            account.getBalance().decreaseAmount(new BigDecimal(amount));
            accountService.save(account);

            transactionRepository.save(trans);
            return trans;
        }



    }
    public Transaction deposit(Integer id, String amount) throws Exception {
        Account account = accountService.findById(id);
        Transaction trans = new Transaction(account, amount);
        account.getBalance().increaseAmount(new BigDecimal(amount));
        accountService.save(account);
        return transactionRepository.save(trans);

    }


}
