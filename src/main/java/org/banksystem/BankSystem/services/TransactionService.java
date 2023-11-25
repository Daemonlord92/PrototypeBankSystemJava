package org.banksystem.BankSystem.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.Entity.Transaction;
import org.banksystem.BankSystem.Enum.TransactionStatus;
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
    private final TransactionProcessingService transactionProcessingService;
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
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setUser(userRepository.findById(newTransaction.getFromAccount()).orElseThrow());
        Transaction savedTransaction = transactionRepository.save(transaction);
        transactionProcessingService.processTransaction(savedTransaction.getId());
        return savedTransaction;
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()) {
            throw new EntityNotFoundException("Transaction not found");
        }
        return transaction;
    }

    public List<Transaction> getAllTransactionsByUserId(Integer id) {
        return transactionRepository.getTransactionsByUserId(id);
    }
}
