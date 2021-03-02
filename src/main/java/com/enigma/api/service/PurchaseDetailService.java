package com.enigma.api.service;

import com.enigma.api.entity.PurchaseDetail;

import java.util.List;

public interface PurchaseDetailService {
    public void registerPurchaseDetail(PurchaseDetail purchaseDetail);
    public void saveAllPurchaseDetail(List<PurchaseDetail> purchaseDetailList);
    public PurchaseDetail getPurchaseDetailById(String id);
}
