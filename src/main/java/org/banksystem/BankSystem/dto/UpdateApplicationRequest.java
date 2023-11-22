package org.banksystem.BankSystem.dto;

import lombok.Data;
import org.banksystem.BankSystem.Enum.HireStatus;
import org.banksystem.BankSystem.Enum.UserRole;
@Data
public class UpdateApplicationRequest {
    private Integer id;
    private HireStatus status;
}
