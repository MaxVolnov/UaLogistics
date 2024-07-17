package com.logistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "admin/admin-home";
    }

    @GetMapping("/requestors")
    public String requestors() {
        return "requestors/requestor-list";
    }

    @GetMapping("/donators")
    public String donators() {
        return "donator/donators-list";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders/orders-list";
    }

    @GetMapping("/products")
    public String products() {
        return "products/products-list";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/admin-home";
    }
}
