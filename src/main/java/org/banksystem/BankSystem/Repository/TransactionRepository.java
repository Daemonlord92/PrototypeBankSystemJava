package org.banksystem.BankSystem.Repository;

import org.banksystem.BankSystem.Entity.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import java.util.ArrayList;


public interface TransactionRepository extends ListCrudRepository<Transaction, Integer> {
    Transaction getTransactionById(Integer id);
    ArrayList<Transaction> getTransactionByUserId(Integer id);
}
