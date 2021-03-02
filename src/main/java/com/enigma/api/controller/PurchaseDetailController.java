package com.enigma.api.controller;

import com.enigma.api.entity.PurchaseDetail;
import com.enigma.api.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseDetailController {
    @Autowired
    PurchaseDetailService purchaseDetailService;

    @PostMapping("/purchaseDetails")
    public void savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail){
        purchaseDetailService.registerPurchaseDetail(purchaseDetail);
    }

    @PostMapping("/allPurchaseDetails")
    public void saveAllPurchaseDetail(@RequestBody List<PurchaseDetail> purchaseDetailList){
        purchaseDetailService.saveAllPurchaseDetail(purchaseDetailList);
    }

    @GetMapping("purchaseDetails/{id}")
    public PurchaseDetail getPurchaseDetailById(@PathVariable String id){
        return purchaseDetailService.getPurchaseDetailById(id);
    }


}
