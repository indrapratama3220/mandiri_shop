package com.enigma.api.controller;

import com.enigma.api.dto.TransactionDTO;
import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import com.enigma.api.response.ApiResponse;
import com.enigma.api.service.PurchaseService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchases")
    public void savePurchase(@RequestBody Purchase purchase) {
        purchaseService.registerPurchase(purchase);
    }

    @GetMapping("/purchases/{id}")
    public Purchase getPurchaseById(@PathVariable String id){
        return purchaseService.getPurchaseById(id);
    }

    @PostMapping("/transaction")
    public Pocket transaction(@RequestBody TransactionDTO transactionDTO){
        return purchaseService.transaction(transactionDTO);

    }
}
