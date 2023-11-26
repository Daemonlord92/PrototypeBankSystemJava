package org.banksystem.BankSystem.services;

import jakarta.persistence.EntityNotFoundException;
import org.banksystem.BankSystem.Entity.Application;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.HireStatus;
import org.banksystem.BankSystem.Repository.ApplicationRepository;
import org.banksystem.BankSystem.Repository.BankRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.banksystem.BankSystem.dto.ApplicationSuccessfulResponse;
import org.banksystem.BankSystem.dto.PostNewApplication;
import org.banksystem.BankSystem.dto.UpdateApplicationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final BankRepository bankRepository;

    public ApplicationService(
            UserRepository userRepository,
            ApplicationRepository applicationRepository,
            BankRepository bankRepository
    ) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.bankRepository = bankRepository;
    }

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

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public String updateApplicationStatus(UpdateApplicationRequest updateApplicationRequest) {
        Optional<Application> application = Optional.of(applicationRepository.findById(updateApplicationRequest.getId())
                .orElseThrow(EntityNotFoundException::new));
        application.get().setStatus(updateApplicationRequest.getStatus());
        applicationRepository.save(application.get());
        if(updateApplicationRequest.getStatus().equals(HireStatus.HIRED)) {
            Optional<User> user = userRepository.findByEmail(application.get().getEmail());
            user.get().setHired(true);
            userRepository.save(user.get());
        }
        return "Update Successful";
    }

    public Optional<Application> getApplicationById(Integer id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new EntityNotFoundException("Application not found");
        }
        return application;
    }
}
