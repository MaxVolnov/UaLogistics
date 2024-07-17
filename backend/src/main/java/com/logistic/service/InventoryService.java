package com.logistic.service;

import com.logistic.model.Item;
import com.logistic.model.Warehouse;
import com.logistic.repository.ItemRepository;
import com.logistic.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public InventoryService(ItemRepository itemRepository, WarehouseRepository warehouseRepository) {
        this.itemRepository = itemRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Item addItem(Item item, Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        item.setWarehouse(warehouse);
        return itemRepository.save(item);
    }

    public Item updateItemQuantity(Long itemId, Integer newQuantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setQuantity(newQuantity);
        return itemRepository.save(item);
    }

    public List<Item> getItemsByWarehouse(Long warehouseId) {
        return itemRepository.findByWarehouseId(warehouseId);
    }

    public List<Item> searchItemsByName(String name) {
        return itemRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Item> getItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId);
    }
}