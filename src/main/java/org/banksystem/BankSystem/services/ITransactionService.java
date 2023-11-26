package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Transaction;
import org.banksystem.BankSystem.dto.PostNewTransaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    public Transaction createNewTransaction(PostNewTransaction newTransaction);
    public Optional<Transaction> getTransactionById(Integer id);
    public List<Transaction> getAllTransactionsByUserId(Integer id);

    List<Transaction> getAllTransactions();
}
