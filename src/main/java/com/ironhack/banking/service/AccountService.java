package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.accounts.StudentChecking;
import com.ironhack.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderService accountHolderService;

    public List<Account> findAll() throws Exception {
        List<Account> result = accountRepository.findAll();
        if (result.size() == 0) throw new Exception("No accounts in Data Base");
        return result;
    }

    public Account findById(Integer id) throws Exception {
        Optional<Account> result = accountRepository.findById(id);
        if (!result.isPresent()) throw new Exception("No account found with that id");
        return result.get();
        }

    public Account saveNew(Account account) throws Exception {
        Date SQLbirthDay = accountHolderService.findById(account.getPrimaryOwner().getId()).getDateOfBirth();
        Date birthDay = new Date(SQLbirthDay.getTime());
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localBD = birthDay.toInstant().atZone(defaultZoneId).toLocalDate();
        LocalDate currentDay = LocalDate.now();
        Period p = Period.between(localBD, currentDay);
        if (p.getYears()<24) {
            StudentChecking student = new StudentChecking(account.getBalance().getAmount().toString(), account.getPrimaryOwner(), account.getSecondaryOwner());
            accountRepository.save(student);
            return student;
        }else{
            Checking newAccount = new Checking(account.getBalance().getAmount().toString(), account.getPrimaryOwner(), account.getSecondaryOwner());
            accountRepository.save(account);
            return newAccount;
        }
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
