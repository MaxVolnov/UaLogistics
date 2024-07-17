package com.logistic.repository;

import com.logistic.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByWarehouseId(Long warehouseId);
    List<Item> findByNameContainingIgnoreCase(String name);
}