package org.banksystem.BankSystem;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;

@SpringBootApplication
public class BankSystemApplication implements ApplicationRunner {
	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
	}
}
