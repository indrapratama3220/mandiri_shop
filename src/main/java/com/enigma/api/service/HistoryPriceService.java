package com.enigma.api.service;

import com.enigma.api.entity.HistoryPrice;

import java.util.List;

public interface HistoryPriceService {
    public void saveHistoryPrice(HistoryPrice historyPrice);
    public List<HistoryPrice> getAllHistoryPrice();
    public List<HistoryPrice> getHistoryPriceByProduct(String productId);
}
