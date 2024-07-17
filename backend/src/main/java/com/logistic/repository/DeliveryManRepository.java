package com.logistic.repository;

import com.logistic.model.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan, Long> {
    @Query("SELECT d FROM DeliveryMan d WHERE d.name = ?1")
    List<DeliveryMan> findByName(String name);

    @Query("SELECT d FROM DeliveryMan d WHERE d.email = ?1")
    DeliveryMan findByEmail(String email);

    @Query("SELECT d FROM DeliveryMan d WHERE d.phoneNumber = ?1")
    DeliveryMan findByPhoneNumber(String phoneNumber);

    @Query("SELECT d FROM DeliveryMan d WHERE d.id = ?1")
    Optional<DeliveryMan> findById(Long id);
}
