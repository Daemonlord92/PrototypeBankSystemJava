package org.banksystem.BankSystem;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.UserRole;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableAsync
@EnableScheduling
public class BankSystemApplication implements ApplicationRunner {
	private final BankRepository bankRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public BankSystemApplication(BankRepository bankRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.bankRepository = bankRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Bank bank = Bank.builder().name("Horror-bank").balance(50000000.0).build();
		bankRepository.save(bank);
		User user = User.builder()
				.email("admin@horrorbank.com")
				.firstName("Matt")
				.lastName("Martin")
				.address("106 Mace St")
				.hired(true)
				.role(UserRole.ROLE_ADMIN)
				.password(passwordEncoder.encode("Gudmord92!"))
				.balance(500000)
				.bank(bank)
				.build();
		bank.setEmployees(List.of(user));
		bankRepository.save(bank);
		userRepository.save(user);

	}
}
