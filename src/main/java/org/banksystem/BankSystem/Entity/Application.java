package org.banksystem.BankSystem.Entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banksystem.BankSystem.Enum.HireStatus;
import org.banksystem.BankSystem.Enum.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application")
public class Application {
    private String email;
    private UserRole role;
    private HireStatus status;
}
