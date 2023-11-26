package org.banksystem.BankSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.banksystem.BankSystem.Enum.TransactionStatus;

import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double amount;
    private Integer fromAccount;
    private Integer toAccount;
    public Long postDate;
    private TransactionStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
