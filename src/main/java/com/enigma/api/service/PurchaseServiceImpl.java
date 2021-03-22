package com.enigma.api.service;

import com.enigma.api.dto.TransactionDTO;
import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import com.enigma.api.entity.PurchaseDetail;
import com.enigma.api.exception.DataNotFoundException;
import com.enigma.api.repository.PocketRepository;
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

    @Autowired
    PocketRepository pocketRepository;

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

    @Override
    public void transaction(TransactionDTO transactionDTO) {
        Purchase purchase = purchaseRepository.save(transactionDTO.getPurchase());
        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetailList()){
            purchaseDetail.setPurchase(purchase);
            purchaseDetailService.registerPurchaseDetail(purchaseDetail);
            if(!pocketRepository.existsById(transactionDTO.getIdPocket())){
                String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "customer", transactionDTO.getIdPocket());
                throw new DataNotFoundException(message);
            }
            Pocket poc = pocketRepository.findById(transactionDTO.getIdPocket()).get();
            Pocket pocket = new Pocket();
            pocket.setId(poc.getId());
            pocket.setPocketName(poc.getPocketName());
            pocket.setCustomer(poc.getCustomer());
            pocket.setProduct(poc.getProduct());
            if(purchase.getPurchaseType() == 0) {
                pocket.setPocketQty(poc.getPocketQty() - purchaseDetail.getQuantityInGram());
            }else if (purchase.getPurchaseType() == 1){
                pocket.setPocketQty(poc.getPocketQty() + purchaseDetail.getQuantityInGram());
            }else {
                pocket.setPocketQty(poc.getPocketQty());
            }

            pocketRepository.save(pocket);
        }

    }
}
