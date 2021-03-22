package com.enigma.api.controller;

import com.enigma.api.dto.TransactionDTO;
import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import com.enigma.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

//    @PostMapping("/purchases")
//    public void savePurchase(@RequestBody Purchase purchase) {
//        purchaseService.registerPurchase(purchase);
//    }

    @GetMapping("/purchases/{id}")
    public Purchase getPurchaseById(@PathVariable String id){
        return purchaseService.getPurchaseById(id);
    }



    @PostMapping("/purchases")
    public Pocket transaction(@RequestBody TransactionDTO transactionDTO){
        return purchaseService.transaction(transactionDTO);
    }

    @GetMapping("/purchases/customer/{customerId}")
    public Page<Purchase> getPurchaseByCustomerId(@PathVariable String customerId,
                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size", defaultValue = "10") Integer sizePerPage){
        Pageable pageable = PageRequest.of(page, sizePerPage);
        return purchaseService.getPurchaseByCustomerId(customerId, pageable);
    }
}
