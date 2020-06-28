package com.ironhack.banking;

import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.enums.Status;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingApplication {

	public BankingApplication(){
	}

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

}
