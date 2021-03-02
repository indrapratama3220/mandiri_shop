package com.enigma.api.service;

import com.enigma.api.entity.Purchase;
import com.enigma.api.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public void registerPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }
}
