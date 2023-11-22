package org.banksystem.BankSystem.Controllers;

import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.dto.AuthRequest;
import org.banksystem.BankSystem.dto.AuthResponse;
import org.banksystem.BankSystem.dto.CreateUserDto;
import org.banksystem.BankSystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> postNewUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createNewUser(createUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> postLogin(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.login(authRequest));
    }
}
