package com.logistic.service;

import com.logistic.model.Order;
import com.logistic.model.OrderStatus;
import com.logistic.model.Request;
import com.logistic.model.User;
import com.logistic.repository.OrderRepository;
import com.logistic.repository.RequestRepository;
import com.logistic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RequestRepository requestRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long requestId, Long userId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setRequest(request);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy(user);

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByRequest(Long requestId) {
        return orderRepository.findByRequestId(requestId);
    }

    public List<Order> getOrdersCreatedByUser(Long userId) {
        return orderRepository.findByCreatedById(userId);
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}