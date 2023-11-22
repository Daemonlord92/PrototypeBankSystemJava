package org.banksystem.BankSystem.services;

import lombok.RequiredArgsConstructor;
import org.banksystem.BankSystem.Entity.Application;
import org.banksystem.BankSystem.Repository.ApplicationRepository;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.dto.ApplicationSuccessfulResponse;
import org.banksystem.BankSystem.dto.PostNewApplication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationSuccessfulResponse applyForBankPosition(PostNewApplication postNewApplication) {
        Application application = Application.builder()
                .email(postNewApplication.getEmail())
                .role(postNewApplication.getRole())
                .build();
        applicationRepository.save(application);
        return ApplicationSuccessfulResponse.builder()
                .message("Application is successful!")
                .build();
    }
}
