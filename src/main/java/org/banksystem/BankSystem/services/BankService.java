package org.banksystem.BankSystem.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.UserRole;
import org.banksystem.BankSystem.Repository.ApplicationRepository;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.banksystem.BankSystem.dto.PostNewBankRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;


    public Bank createNewBank(PostNewBankRequest postNewBankRequest) {
        Bank bank = Bank.builder()
                .name(postNewBankRequest.getName())
                .balance(postNewBankRequest.getBalance())
                .build();
        return bankRepository.save(bank);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public List<User> getAllEmployeesByBankId(Integer bankId) {
        if(bankRepository.findById(bankId).isEmpty()) {
            throw new EntityNotFoundException("Bank not found");
        }
        return bankRepository.findById(bankId).get().getEmployees();
    }

    public String deleteBank(Integer bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        if (bank.isEmpty()) {
            throw new EntityNotFoundException("Bank not found");
        }
        for (User user: bank.get().getEmployees()
             ) {
            user.setRole(UserRole.ROLE_CLIENT);
            user.setBank(null);
            userRepository.save(user);
        }
        bankRepository.delete(bank.get());
        return "Banks deleted";
    }
}
