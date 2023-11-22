package org.banksystem.BankSystem.dto;

import lombok.*;
import org.banksystem.BankSystem.Enum.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private UserRole role;
    private float balance;
    private boolean hired = false;
}
