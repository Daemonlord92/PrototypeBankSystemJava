package org.banksystem.BankSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banksystem.BankSystem.Enum.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostNewApplication {
    private String email;
    private UserRole role;
}
