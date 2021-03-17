package com.enigma.api.service;

import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import com.enigma.api.entity.PurchaseDetail;
import com.enigma.api.repository.ProductRepository;
import com.enigma.api.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void registerPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetailRepository.save(purchaseDetail);
    }

    @Override
    public void saveAllPurchaseDetail(List<PurchaseDetail> purchaseDetailList) {
//        Optional<Product> optionalProduct = productRepository.findById(purchaseDetailList.get());
//        if(optionalProduct.isPresent()){
        List<PurchaseDetail> purchaseDetailList1 = new ArrayList<>(purchaseDetailList);
        purchaseDetailRepository.saveAll(purchaseDetailList1);
    }

    @Override
    public PurchaseDetail getPurchaseDetailById(String id) {
        return purchaseDetailRepository.findById(id).get();
    }

    @Override
    public void transaction(Pocket balance, Purchase purchase, PurchaseDetail purchaseDetail) {

    }
}
