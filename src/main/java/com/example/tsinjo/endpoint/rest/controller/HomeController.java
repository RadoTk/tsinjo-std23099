package com.example.tsinjo.endpoint.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.tsinjo.model.Donation;
import com.example.tsinjo.service.DonationService;
import com.example.tsinjo.service.HelpService;

@Controller
public class HomeController {

    private final DonationService donationService;
    private final HelpService helpService;

    public HomeController(DonationService donationService, HelpService helpService) {
        this.donationService = donationService;
        this.helpService = helpService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("donations", donationService.findAll());
        model.addAttribute("aides", helpService.findAll());
        model.addAttribute("newDonation", new Donation());
        return "home";
    }

    @PostMapping("/don")
    public String submitDonation(@ModelAttribute Donation donation) {
        donationService.save(donation);
        return "redirect:/";
    }
}
