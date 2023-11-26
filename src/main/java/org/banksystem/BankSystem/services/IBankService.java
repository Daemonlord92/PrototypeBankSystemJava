package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.dto.PostNewBankRequest;

import java.util.List;

public interface IBankService {
    public Bank createNewBank(PostNewBankRequest postNewBankRequest);
    public List<Bank> getAllBanks();
    public List<User> getAllEmployeesByBankId(Integer bankId);
    public String deleteBank(Integer bankId);
}
