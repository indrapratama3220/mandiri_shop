package com.enigma.api.repository;

import com.enigma.api.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    public Page<Purchase> findAllByCustomerId(String customerId, Pageable page);
}
