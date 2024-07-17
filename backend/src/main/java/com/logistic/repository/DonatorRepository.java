package com.logistic.repository;

import com.logistic.model.Donator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonatorRepository extends JpaRepository<Donator, Long> {
    Optional<Donator> findById(Long id);
    void deleteById(Long id);

    @Query("SELECT d FROM Donator d WHERE d.name = ?1")
    List<Donator> findByName(String name);

    @Query("SELECT d FROM Donator d WHERE d.city = ?1")
    List<Donator> findByCity(String city);

    @Query("SELECT d FROM Donator d WHERE d.country = ?1")
    List<Donator> findByCountry(String country);

    @Query("SELECT d FROM Donator d WHERE d.email = ?1")
    Donator findByEmail(String email);

    @Query("SELECT d FROM Donator d WHERE d.postalCode = ?1")
    List<Donator> findByPostalCode(String postalCode);
}
