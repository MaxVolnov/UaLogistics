package com.logistic.repository;

import com.logistic.model.Product;
import com.logistic.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p")
    List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findByCategory(ProductCategory category);

    @Query("SELECT p FROM Product p WHERE p.price < ?1")
    List<Product> findByPriceLessThan(Double price);

    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1%")
    List<Product> findByDescriptionContaining(String keyword);
}
