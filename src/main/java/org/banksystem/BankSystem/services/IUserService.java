package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.dto.AuthRequest;
import org.banksystem.BankSystem.dto.AuthResponse;
import org.banksystem.BankSystem.dto.CreateUserDto;
import org.banksystem.BankSystem.dto.UpdateUserProfileRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public AuthResponse createNewUser(CreateUserDto createUserDto);
    public Optional<User> getUserById(Integer id);
    public List<User> getAllUser();
    public AuthResponse login(AuthRequest authRequest);
    public String terminateEmployee(Integer id);
    public void deleteUser(Integer id);
    public Optional<User> updateUser(UpdateUserProfileRequest request);
}
