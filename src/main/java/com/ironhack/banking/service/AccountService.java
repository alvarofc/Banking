package com.ironhack.banking.service;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.accounts.StudentChecking;
import com.ironhack.banking.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public List<Account> findAll() throws Exception {
        List<Account> result = accountRepository.findAll();
        if (result.size() == 0) throw new Exception("No accounts in Data Base");
        LOGGER.info("Looked for all Accounts");
        return result;
    }

    public Account findById(Integer id) throws Exception {
        Optional<Account> result = accountRepository.findById(id);
        if (!result.isPresent()) throw new Exception("No account found with that id");
        LOGGER.info("Looked for Account" + id);
        if (result.get().getChargedDate()==null){
            LocalDate currentDay = LocalDate.now();
            Date creation = new Date(result.get().getCreationDate().getTime());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localCreation = creation.toInstant().atZone(defaultZoneId).toLocalDate();
            Period p = Period.between(localCreation, currentDay);
            if(p.getYears()>1) {
                result.get().getBalance().decreaseAmount(result.get().getMaintenanceFee());
                result.get().setChargedDate(new Date());
                accountRepository.save(result.get());
            }
        }else {
            LocalDate currentDay = LocalDate.now();
            Date charged = new Date(result.get().getChargedDate().getTime());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localCharged = charged.toInstant().atZone(defaultZoneId).toLocalDate();
            Period p = Period.between(localCharged, currentDay);
            if(p.getYears()>1) {
                result.get().getBalance().decreaseAmount(result.get().getMaintenanceFee());
                result.get().setChargedDate(new Date());
                accountRepository.save(result.get());
            }
        }
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
            LOGGER.info("New Student Account" + student.getId());
            return student;
        }else{
            Checking newAccount = new Checking(account.getBalance().getAmount().toString(), account.getPrimaryOwner(), account.getSecondaryOwner());
            accountRepository.save(account);
            LOGGER.info("New Checking Account created" + newAccount.getId());
            return newAccount;
        }
    }

    public Account save(Account account) {
        LOGGER.info("Account updated" + account.getId());
        return accountRepository.save(account);
    }
}
