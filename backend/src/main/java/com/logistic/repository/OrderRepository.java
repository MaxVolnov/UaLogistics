package com.logistic.repository;

import com.logistic.enums.DonationType;
import com.logistic.model.Order;
import com.logistic.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> findById(Long id);

    @Query("SELECT o FROM Order o WHERE o.status = ?1")
    List<Order> findByStatus(OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.requestor.id = ?1")
    List<Order> findByRequestorId(Long requestorId);

    @Query("SELECT o FROM Order o WHERE o.donator.id = ?1")
    List<Order> findByDonatorId(Long donatorId);

    @Query("SELECT o FROM Order o WHERE o.donationType = ?1")
    List<Order> findByDonationType(DonationType donationType);
}
