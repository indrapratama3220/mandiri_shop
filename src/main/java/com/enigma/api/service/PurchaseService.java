package com.enigma.api.service;

import com.enigma.api.entity.Purchase;

public interface PurchaseService {
    public void registerPurchase(Purchase purchase);
    public  Purchase getPurchaseById(String id);
}
