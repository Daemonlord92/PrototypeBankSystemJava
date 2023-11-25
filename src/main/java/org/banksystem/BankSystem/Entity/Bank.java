package org.banksystem.BankSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double balance;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<User> employees;
}
