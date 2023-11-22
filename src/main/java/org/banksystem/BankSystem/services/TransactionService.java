package org.banksystem.BankSystem.services;

import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.Entity.Transaction;
import org.banksystem.BankSystem.Repository.TransactionRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.banksystem.BankSystem.dto.PostNewTransaction;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }


    public Transaction createNewTransaction(PostNewTransaction newTransaction) {
        Transaction transaction = new Transaction();
        transaction.setPostDate(new Date().getTime());
        transaction.setAmount(newTransaction.getAmount());
        transaction.setFromAccount(newTransaction.getFromAccount());
        transaction.setToAccount(newTransaction.getToAccount());
        transaction.setUser(userRepository.findById(newTransaction.getFromAccount()).orElseThrow());
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactionsByUserId(Integer id) {
        return transactionRepository.getTransactionByUserId(id);
    }
}
