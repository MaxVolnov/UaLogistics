package com.logistic.repository;

import com.logistic.model.Requestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestorRepository extends JpaRepository<Requestor, Long> {
    @Query("SELECT r FROM Requestor r WHERE r.id = ?1")
    Requestor findById(long id);

    // Дополнительные запросы
    @Query("SELECT r FROM Requestor r WHERE r.email = ?1")
    Requestor findByEmail(String email);

    @Query("SELECT r FROM Requestor r WHERE r.phone = ?1")
    List<Requestor> findByPhone(String phone);

    @Query("SELECT r FROM Requestor r WHERE r.name LIKE %?1%")
    List<Requestor> findByNameContaining(String nameFragment);
}
