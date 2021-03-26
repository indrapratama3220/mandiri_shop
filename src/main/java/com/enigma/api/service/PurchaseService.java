package com.enigma.api.service;

import com.enigma.api.dto.TransactionDTO;
import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PurchaseService {
    public void registerPurchase(Purchase purchase);

    public Purchase getPurchaseById(String id);

    public Pocket transaction(TransactionDTO transactionDTO);

    public Page<Purchase> getPurchaseByCustomerId(String customerId, Pageable pageable);
}
