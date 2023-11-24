package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Transaction;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.TransactionStatus;
import org.banksystem.BankSystem.Repository.TransactionRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionProcessingService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionProcessingService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Async
    public CompletableFuture<Void> processTransaction(Integer id) {
        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(TransactionStatus.PENDING.equals(transaction.get().getStatus())) {
            processTransactionLogic(transaction.get());
        }

        return CompletableFuture.completedFuture(null);
    }

    private void processTransactionLogic(Transaction transaction) {
        User fromUser = userRepository.findById(transaction.getFromAccount()).get();

        if(fromUser.getBalance() >= transaction.getAmount()) {
            fromUser.setBalance((float) (fromUser.getBalance() - transaction.getAmount()));
            transaction.setStatus(TransactionStatus.COMPLETE);
            userRepository.save(fromUser);
        } else {
            transaction.setStatus(TransactionStatus.FAILED);
        }
        transactionRepository.save(transaction);
    }
}
