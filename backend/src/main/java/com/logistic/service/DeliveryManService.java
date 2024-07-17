package com.logistic.service;

import com.logistic.model.DeliveryMan;
import com.logistic.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryManService {

    private final DeliveryManRepository deliveryManRepository;

    @Autowired
    public DeliveryManService(DeliveryManRepository deliveryManRepository) {
        this.deliveryManRepository = deliveryManRepository;
    }

    public List<DeliveryMan> findAllDeliveryMen() {
        return deliveryManRepository.findAll();
    }

    public DeliveryMan findDeliveryManById(Long id) {
        return deliveryManRepository.findById(id).orElse(null);
    }

    public DeliveryMan saveDeliveryMan(DeliveryMan deliveryMan) {
        return deliveryManRepository.save(deliveryMan);
    }

    public void deleteDeliveryMan(Long id) {
        deliveryManRepository.deleteById(id);
    }
}
