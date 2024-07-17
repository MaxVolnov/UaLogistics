package com.logistic.service;

import com.logistic.model.Donator;
import com.logistic.repository.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonatorService {

    private final DonatorRepository donatorRepository;

    @Autowired
    public DonatorService(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    public List<Donator> findAllDonators() {
        return donatorRepository.findAll();
    }

    public Donator findDonatorById(Long id) {
        return donatorRepository.findById(id).orElse(null);
    }

    public Donator saveDonator(Donator donator) {
        return donatorRepository.save(donator);
    }

    public void deleteDonator(Long id) {
        donatorRepository.deleteById(id);
    }
}
