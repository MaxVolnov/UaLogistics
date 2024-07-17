package com.logistic.controller;

import com.logistic.model.Order;
import com.logistic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "orders/order-list";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orders/order-form";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders/";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "orders/order-form";
        } else {
            return "redirect:/orders/";
        }
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("order") Order order) {
        Order existingOrder = orderService.findOrderById(id);
        if (existingOrder != null) {
            existingOrder.setStatus(order.getStatus());
            existingOrder.setDonator(order.getDonator());
            existingOrder.setDonationType(order.getDonationType());
            existingOrder.setRequestedQuantity(order.getRequestedQuantity());
            orderService.saveOrder(existingOrder);
        }
        return "redirect:/orders/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders/";
    }
}
