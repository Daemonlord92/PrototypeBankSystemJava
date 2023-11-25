package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaydayProcessingService {
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    public PaydayProcessingService(UserRepository userRepository, BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    @Scheduled(cron = "0 0 0 * * FRI")
    public void payday() {
        List<Bank> bankList = bankRepository.findAll();
        for(Bank bank : bankList) {
            double payday = 0;
            for (User user: bank.getEmployees()) {
                switch (user.getRole()) {
                    case ROLE_ADMIN:
                        payday += 5000;
                        user.setBalance(user.getBalance() + 5000);
                        userRepository.save(user);
                        break;
                    case ROLE_TELLER:
                        payday += 2000;
                        user.setBalance(user.getBalance() + 2000);
                        userRepository.save(user);
                        break;
                    case ROLE_MANAGER:
                        payday += 7000;
                        user.setBalance(user.getBalance() + 7000);
                        userRepository.save(user);
                        break;
                }
            }
            bank.setBalance(bank.getBalance() - payday);
        }
    }
}
