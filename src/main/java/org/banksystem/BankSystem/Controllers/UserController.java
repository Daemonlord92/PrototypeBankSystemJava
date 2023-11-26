package org.banksystem.BankSystem.Controllers;

import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.dto.UpdateUserProfileRequest;
import org.banksystem.BankSystem.services.IUserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/user/")
    public ResponseEntity<Optional<User>> getUserById(@RequestParam Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/updateUserProfile")
    public ResponseEntity<Optional<User>> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @DeleteMapping("/closeAccount/{id}")
    public ResponseEntity closeAccount(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
