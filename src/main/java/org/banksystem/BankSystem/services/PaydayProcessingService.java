package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    }
}
