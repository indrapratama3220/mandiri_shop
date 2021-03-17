package com.enigma.api.service;

import com.enigma.api.entity.Purchase;
import com.enigma.api.entity.PurchaseDetail;
import com.enigma.api.repository.ProductRepository;
import com.enigma.api.repository.PurchaseDetailRepository;
import com.enigma.api.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void registerPurchase(Purchase purchase) {
        Purchase purchase1 = purchaseRepository.save(purchase);
        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetailList()){
//            if(!productRepository.existsById(purchaseDetail.getProduct().getId())){
//                String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "product" ,purchaseDetail.getProduct().getId());
//                throw new DataNotFoundException(message);
//            }
            purchaseDetail.setPurchase(purchase1);
            purchaseDetailService.registerPurchaseDetail(purchaseDetail);
        }
    }

    @Override
    public Purchase getPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }
}
