package org.banksystem.BankSystem.Controllers;

import org.banksystem.BankSystem.services.BankService;
import org.banksystem.BankSystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final UserService userService;
    private final BankService bankService;

    public BankController(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    @PutMapping("/terminate")
    public ResponseEntity<String> terminateEmployee(Integer id) {
        return ResponseEntity.ok(userService.terminateEmployee(id));
    }
}
