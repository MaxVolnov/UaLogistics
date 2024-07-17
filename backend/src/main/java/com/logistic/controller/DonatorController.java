package com.logistic.controller;

import com.logistic.model.Donator;
import com.logistic.service.DonatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/donators")
public class DonatorController {

    private final DonatorService donatorService;

    @Autowired
    public DonatorController(DonatorService donatorService) {
        this.donatorService = donatorService;
    }

    @GetMapping("/")
    public String listDonators(Model model) {
        model.addAttribute("donators", donatorService.findAllDonators());
        return "donator/donator-list";
    }

    @GetMapping("/new")
    public String showNewDonatorForm(Model model) {
        model.addAttribute("donator", new Donator());
        return "donator/donator-form";
    }

    @PostMapping("/save")
    public String saveDonator(@ModelAttribute("donator") Donator donator) {
        donatorService.saveDonator(donator);
        return "redirect:/donators/";
    }

    @GetMapping("/edit/{id}")
    public String showEditDonatorForm(@PathVariable Long id, Model model) {
        Donator donator = donatorService.findDonatorById(id);
        if (donator != null) {
            model.addAttribute("donator", donator);
            return "donator/donator-form";
        } else {
            return "redirect:/donators/";
        }
    }

    @PostMapping("/update/{id}")
    public String updateDonator(@PathVariable Long id, @ModelAttribute("donator") Donator donator) {
        Donator existingDonator = donatorService.findDonatorById(id);
        if (existingDonator != null) {
            existingDonator.setName(donator.getName());
            existingDonator.setEmail(donator.getEmail());
            existingDonator.setAddress(donator.getAddress());
            existingDonator.setCity(donator.getCity());
            existingDonator.setPostalCode(donator.getPostalCode());
            existingDonator.setCountry(donator.getCountry());
            existingDonator.setPhoneNumber(donator.getPhoneNumber());
            donatorService.saveDonator(existingDonator);
        }
        return "redirect:/donators/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDonator(@PathVariable Long id) {
        donatorService.deleteDonator(id);
        return "redirect:/donators/";
    }
}
