package org.banksystem.BankSystem.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Repository.ApplicationRepository;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.dto.PostNewBankRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;
    private final ApplicationRepository applicationRepository;


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
}
