package org.banksystem.BankSystem.Controllers;

import org.banksystem.BankSystem.Entity.Transaction;
import org.banksystem.BankSystem.dto.PostNewTransaction;
import org.banksystem.BankSystem.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    @Secured({"TELLER", "MANAGER", "ADMIN"})
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/userTransaction/")
    @Secured({"CLIENT", "TELLER", "MANAGER", "ADMIN"})
    public ResponseEntity<List<Transaction>> getAllUserTransactions(@RequestParam Integer id) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(id));
    }

    @GetMapping("/transaction/")
    @Secured({"CLIENT", "TELLER", "MANAGER", "ADMIN"})
    public ResponseEntity<Optional<Transaction>> getTransactionById(@RequestParam Integer id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping("/")
    @Secured({"CLIENT", "TELLER", "MANAGER", "ADMIN"})
    public ResponseEntity<Transaction> postNewTransaction(PostNewTransaction newTransaction) {
            return ResponseEntity.ok(transactionService.createNewTransaction(newTransaction));
    }
}
