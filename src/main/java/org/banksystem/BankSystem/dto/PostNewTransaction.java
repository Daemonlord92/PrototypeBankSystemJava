package org.banksystem.BankSystem.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostNewTransaction {
    private double amount;
    private int fromAccount;
    private int toAccount;
}
