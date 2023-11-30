package org.banksystem.BankSystem.Controllers;

import org.banksystem.BankSystem.Entity.Bank;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.dto.PostNewBankRequest;
import org.banksystem.BankSystem.services.IBankService;
import org.banksystem.BankSystem.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/bank")
public class BankController {
    private final IUserService userService;
    private final IBankService bankService;

    public BankController(IUserService userService, IBankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    @PostMapping("/createNewBank")
    public ResponseEntity<Bank> postNewBank(PostNewBankRequest postNewBankRequest) {
        return ResponseEntity.ok(bankService.createNewBank(postNewBankRequest));
    }

    @GetMapping("/getBankInformation/{bankId}")
    public ResponseEntity<Optional<Bank>> getBankInformation(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.getBankInfoById(bankId));
    }

    @GetMapping("/allBanks")
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @GetMapping("/getAllBankEmployees/{bankId}")
    public ResponseEntity<List<User>> getAllBankEmployees(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.getAllEmployeesByBankId(bankId));
    }

    @PutMapping("/terminate")
    public ResponseEntity<String> terminateEmployee(Integer id) {
        return ResponseEntity.ok(userService.terminateEmployee(id));
    }

    @DeleteMapping("/deleteBank/{bankId}")
    public ResponseEntity<String> deleteBank(@PathVariable Integer bankId) {
        return ResponseEntity.ok(bankService.deleteBank(bankId));
    }
}
