package org.banksystem.BankSystem.Repository;

import org.banksystem.BankSystem.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByEmail(String email);
}
