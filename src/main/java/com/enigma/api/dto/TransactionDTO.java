package com.enigma.api.dto;

import com.enigma.api.entity.Customer;
import com.enigma.api.entity.Purchase;
import com.enigma.api.entity.PurchaseDetail;

import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private String idPocket;
    private Purchase purchase;

    public TransactionDTO(String idPocket, Purchase purchase) {
        this.idPocket = idPocket;
        this.purchase = purchase;
    }

    public String getIdPocket() {
        return idPocket;
    }

    public void setIdPocket(String idPocket) {
        this.idPocket = idPocket;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
