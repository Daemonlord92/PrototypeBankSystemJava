package org.banksystem.BankSystem.Controllers;

import org.banksystem.BankSystem.Entity.Application;
import org.banksystem.BankSystem.dto.ApplicationSuccessfulResponse;
import org.banksystem.BankSystem.dto.PostNewApplication;
import org.banksystem.BankSystem.dto.UpdateApplicationRequest;
import org.banksystem.BankSystem.services.ApplicationService;
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
    private final ApplicationService applicationService;

    public BankController(UserService userService, BankService bankService, ApplicationService applicationService) {
        this.userService = userService;
        this.bankService = bankService;
        this.applicationService = applicationService;
    }

    @PutMapping("/apply")
    public ResponseEntity<ApplicationSuccessfulResponse> applyForBankPosition(@RequestBody PostNewApplication postNewApplication) {
        return ResponseEntity.ok(bankService.applyForBankPosition(postNewApplication));
    }

    @GetMapping("/")
    public ResponseEntity<List<Application>> getAllApplication() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<Optional<Application>> getApplicationStatus(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateApplicationStatus(@RequestBody UpdateApplicationRequest updateApplicationRequest) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(updateApplicationRequest));
    }

    @PutMapping("/terminate")
    public ResponseEntity<String> terminateEmployee(Integer id) {
        return ResponseEntity.ok(userService.terminateEmployee(id));
    }
}
