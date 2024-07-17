package com.logistic.controller;

import com.logistic.model.DeliveryMan;
import com.logistic.service.DeliveryManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Controller
@RequestMapping("/deliverymen")
public class DeliveryManController {
    private static final Logger logger = LogManager.getLogger(DeliveryManController.class);

    @Autowired
    private DeliveryManService deliveryManService;

    @GetMapping("/")
    public String listDeliveryMen(Model model) {
        List<DeliveryMan> deliveryMen = deliveryManService.findAllDeliveryMen();
        model.addAttribute("deliveryMen", deliveryMen);
        return "deliverymen/deliveryman-list";
    }

    @GetMapping("/new")
    public String showNewDeliveryManForm(Model model) {
        model.addAttribute("deliveryMan", new DeliveryMan());
        return "deliverymen/deliveryman-form";
    }

    @PostMapping("/save")
    public String saveDeliveryMan(@ModelAttribute("deliveryMan") DeliveryMan deliveryMan) {
        deliveryManService.saveDeliveryMan(deliveryMan);
        return "redirect:/deliverymen/";
    }

    @GetMapping("/edit/{id}")
    public String showEditDeliveryManForm(@PathVariable Long id, Model model) {
        DeliveryMan deliveryMan = deliveryManService.findDeliveryManById(id);
        if (deliveryMan != null) {
            model.addAttribute("deliveryMan", deliveryMan);
            return "deliverymen/deliveryman-form";
        } else {
            return "redirect:/deliverymen/";
        }
    }

    @PostMapping("/update/{id}")
    public String updateDeliveryMan(@PathVariable Long id, @ModelAttribute("deliveryMan") DeliveryMan deliveryMan) {
        DeliveryMan existingDeliveryMan = deliveryManService.findDeliveryManById(id);
        if (existingDeliveryMan != null) {
            existingDeliveryMan.setName(deliveryMan.getName());
            existingDeliveryMan.setEmail(deliveryMan.getEmail());
            existingDeliveryMan.setPhoneNumber(deliveryMan.getPhoneNumber());
            deliveryManService.saveDeliveryMan(existingDeliveryMan);
        }
        return "redirect:/deliverymen/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeliveryMan(@PathVariable Long id) {
        deliveryManService.deleteDeliveryMan(id);
        return "redirect:/deliverymen/";
    }
}
