package com.logistic.repository;

import com.logistic.model.Order;
import com.logistic.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByRequestId(Long requestId);
    List<Order> findByCreatedById(Long userId);
}