package com.example.tsinjo.service;

import com.example.tsinjo.model.Donation;
import com.example.tsinjo.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }
}
