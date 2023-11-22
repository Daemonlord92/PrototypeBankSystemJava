package org.banksystem.BankSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.banksystem.BankSystem.Enum.TransactionStatus;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double amount;
    private Integer fromAccount;
    private Integer toAccount;
    public Long postDate;
    //TODO Setup a Multithreading where the transaction is completed or failed after 5 minutes
    private TransactionStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
