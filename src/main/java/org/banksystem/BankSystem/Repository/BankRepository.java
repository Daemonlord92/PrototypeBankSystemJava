package org.banksystem.BankSystem.Repository;

import org.banksystem.BankSystem.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
