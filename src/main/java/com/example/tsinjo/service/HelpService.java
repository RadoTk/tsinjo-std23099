package com.example.tsinjo.service;

import com.example.tsinjo.model.Help;
import com.example.tsinjo.repository.HelpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {

    private final HelpRepository helpRepository;

    public HelpService(HelpRepository helpRepository) {
        this.helpRepository = helpRepository;
    }

    public List<Help> findAll() {
        return helpRepository.findAll();
    }
}
