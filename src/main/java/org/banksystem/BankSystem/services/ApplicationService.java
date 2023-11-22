package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Application;
import org.banksystem.BankSystem.Entity.User;
import org.banksystem.BankSystem.Enum.HireStatus;
import org.banksystem.BankSystem.Repository.ApplicationRepository;
import org.banksystem.BankSystem.Repository.UserRepository;
import org.banksystem.BankSystem.dto.UpdateApplicationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationService(UserRepository userRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public String updateApplicationStatus(UpdateApplicationRequest updateApplicationRequest) {
        Optional<Application> application = Optional.of(applicationRepository.findById(updateApplicationRequest.getId()).orElseThrow());
        application.get().setStatus(updateApplicationRequest.getStatus());
        applicationRepository.save(application.get());
        if(updateApplicationRequest.getStatus().equals(HireStatus.HIRED)) {
            Optional<User> user = userRepository.findByEmail(application.get().getEmail());
            user.get().setHired(true);
        }
        return "Update Successful";
    }
}
