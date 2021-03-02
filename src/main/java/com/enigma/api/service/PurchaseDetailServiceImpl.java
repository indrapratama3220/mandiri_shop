package com.enigma.api.service;

import com.enigma.api.entity.PurchaseDetail;
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

    @Override
    public void registerPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetailRepository.save(purchaseDetail);
    }

    @Override
    public void saveAllPurchaseDetail(List<PurchaseDetail> purchaseDetailList) {
        List<PurchaseDetail> purchaseDetailList1 = new ArrayList<>(purchaseDetailList);
        purchaseDetailRepository.saveAll(purchaseDetailList1);
    }

    @Override
    public PurchaseDetail getPurchaseDetailById(String id) {
        return purchaseDetailRepository.findById(id).get();
    }
}
