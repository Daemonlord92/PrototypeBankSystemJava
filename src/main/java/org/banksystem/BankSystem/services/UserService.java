package org.banksystem.BankSystem.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.UserRole;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.banksystem.BankSystem.config.JwtService;
import org.banksystem.BankSystem.dto.AuthRequest;
import org.banksystem.BankSystem.dto.AuthResponse;
import org.banksystem.BankSystem.dto.CreateUserDto;
import org.banksystem.BankSystem.dto.UpdateUserProfileRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse createNewUser(CreateUserDto createUserDto) {
        User newUser = User.builder()
                .email(createUserDto.getEmail())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .address(createUserDto.getAddress())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .balance(createUserDto.getBalance())
                .role(createUserDto.getRole())
                .hired(createUserDto.isHired())
                .build();
        userRepository.save(newUser);
        String jwt = jwtService.generateToken(newUser);
        return AuthResponse.builder()
                .token(jwt).build();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt).build();
    }

    public String terminateEmployee(Integer id) {
        Optional<User> user = userRepository.findById(id);
        user.get().setHired(false);
        user.get().setRole(UserRole.ROLE_CLIENT);
        userRepository.save(user.get());
        return "Employee Terminated";
    }

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new EntityNotFoundException("User Doesn't Exists");
        }
        userRepository.delete(user.get());
    }

    public Optional<User> updateUser(UpdateUserProfileRequest request) {
        Optional<User> user = userRepository.findById(request.getId());
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        if (request.getAddress() != user.get().getAddress() || request.getAddress() != null) {
            user.get().setAddress(request.getAddress());
        }
        String passHash = passwordEncoder.encode(request.getPassword());
        if(passHash != user.get().getPassword()) {
            user.get().setPassword(passHash);
        }
        return Optional.of(userRepository.save(user.get()));
    }
}
