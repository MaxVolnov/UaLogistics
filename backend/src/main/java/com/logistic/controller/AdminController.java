package com.logistic.controller;

import com.logistic.model.Admin;
import com.logistic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String listAdmins(Model model) {
        List<Admin> admins = adminService.findAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/admin-list";
    }

    @GetMapping("/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/admin-form";
    }

    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admins/";
    }

    @GetMapping("/edit/{id}")
    public String showEditAdminForm(@PathVariable Long id, Model model) {
        Admin admin = adminService.findAdminById(id);
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin/admin-form";
        } else {
            return "redirect:/admins/";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAdmin(@PathVariable Long id, @ModelAttribute("admin") Admin admin) {
        Admin existingAdmin = adminService.findAdminById(id);
        if (existingAdmin != null) {
            existingAdmin.setUsername(admin.getUsername());
            existingAdmin.setEmail(admin.getEmail());
            // Note: You might want to handle password updates separately for security reasons
            adminService.saveAdmin(existingAdmin);
        }
        return "redirect:/admins/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "redirect:/admins/";
    }
}