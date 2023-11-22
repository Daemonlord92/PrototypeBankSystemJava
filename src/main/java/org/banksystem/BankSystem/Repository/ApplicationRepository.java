package org.banksystem.BankSystem.Repository;

import org.banksystem.BankSystem.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
