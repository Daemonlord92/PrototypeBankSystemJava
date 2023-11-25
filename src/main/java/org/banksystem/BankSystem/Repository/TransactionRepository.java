package org.banksystem.BankSystem.Repository;

import org.banksystem.BankSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;


public interface TransactionRepository extends ListCrudRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transactions WHERE from_account = :userId OR to_account = :userId", nativeQuery = true)
    ArrayList<Transaction> getTransactionsByUserId(@Param("userId") Integer id);
}
