package org.banksystem.BankSystem.services;

import org.banksystem.BankSystem.Entity.Application;
import org.banksystem.BankSystem.dto.ApplicationSuccessfulResponse;
import org.banksystem.BankSystem.dto.PostNewApplication;
import org.banksystem.BankSystem.dto.UpdateApplicationRequest;

import java.util.List;
import java.util.Optional;

public interface IApplicationService {
    public ApplicationSuccessfulResponse applyForBankPosition(PostNewApplication postNewApplication);
    public List<Application> getAllApplications();
    public String updateApplicationStatus(UpdateApplicationRequest updateApplicationRequest);
    public Optional<Application> getApplicationById(Integer id);
}
