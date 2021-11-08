package com.enigma.api.service;

import com.enigma.api.dto.TransactionDTO;
import com.enigma.api.entity.Email;
import com.enigma.api.entity.Pocket;
import com.enigma.api.entity.Purchase;
import com.enigma.api.entity.PurchaseDetail;
import com.enigma.api.exception.DataCreated;
import com.enigma.api.exception.DataNotFoundException;
import com.enigma.api.repository.PocketRepository;
import com.enigma.api.repository.ProductRepository;
import com.enigma.api.repository.PurchaseDetailRepository;
import com.enigma.api.repository.PurchaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

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

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void registerPurchase(Purchase purchase) {
        Purchase purchase1 = purchaseRepository.save(purchase);
        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetailList()) {
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
    public Pocket transaction(TransactionDTO transactionDTO) throws JsonProcessingException {
        System.out.println("INI TRANSACTION DTO");
        System.out.println(transactionDTO.getPurchase());
        Purchase purchase = purchaseRepository.save(transactionDTO.getPurchase());
        Pocket pockett = new Pocket();
        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetailList()) {
            purchaseDetail.setPurchase(purchase);
            purchaseDetailService.registerPurchaseDetail(purchaseDetail);
            if (!pocketRepository.existsById(transactionDTO.getIdPocket())) {
                String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "customer", transactionDTO.getIdPocket());
                throw new DataNotFoundException(message);
            }
            Pocket poc = pocketRepository.findById(transactionDTO.getIdPocket()).get();
            Pocket pocket = new Pocket();
            pocket.setId(poc.getId());
            pocket.setPocketName(poc.getPocketName());
            pocket.setCustomer(poc.getCustomer());
            pocket.setProduct(poc.getProduct());
            if (purchase.getPurchaseType() == 0) {
                pocket.setPocketQty(poc.getPocketQty() - purchaseDetail.getQuantityInGram());
            } else if (purchase.getPurchaseType() == 1) {
                pocket.setPocketQty(poc.getPocketQty() + purchaseDetail.getQuantityInGram());
            } else {
                pocket.setPocketQty(poc.getPocketQty());
            }
            pockett = pocket;
        }

        pocketRepository.save(pockett);
//        String emailMessage = sendEmail(transactionDTO);
//        kafkaTemplate.send("sendemail", emailMessage);
        String message = String.format(DataCreated.CREATED_MESSAGE, purchase.getId());
        throw new DataCreated(message);

    }

    public String sendEmail(TransactionDTO transactionDTO){
        Email email = new Email();
        email.setCustomerId(transactionDTO.getPurchase().getCustomer().getId());
        if(transactionDTO.getPurchase().getPurchaseType() == 1){
            email.setTransactionType("pembelian");
        }
        if(transactionDTO.getPurchase().getPurchaseType() == 0){
            email.setTransactionType("penjualan");
        }
        email.setPrice(transactionDTO.getPurchase().getPurchaseDetailList().get(0).getPrice());
        Double quantity = transactionDTO.getPurchase().getPurchaseDetailList().get(0).getQuantityInGram();
        email.setMessage("Customer dengan id " + email.getCustomerId() +
                " telah melakukan transaksi "+email.getTransactionType()+ " seharga "+email.getPrice() +
                " dengan quantity "+quantity+ " gram.");
        return email.getMessage();
    }

    @Override
    public Page<Purchase> getPurchaseByCustomerId(String customerId, Pageable pageable) {
        return purchaseRepository.findAllByCustomerId(customerId, pageable);
    }
}
