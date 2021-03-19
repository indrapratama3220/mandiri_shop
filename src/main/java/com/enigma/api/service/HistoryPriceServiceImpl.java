package com.enigma.api.service;

import com.enigma.api.entity.HistoryPrice;
import com.enigma.api.repository.HistoryPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryPriceServiceImpl implements HistoryPriceService {

    @Autowired
    HistoryPriceRepository historyPriceRepository;


    @Override
    public void saveHistoryPrice(HistoryPrice historyPrice) {
        historyPriceRepository.save(historyPrice);
    }

    @Override
    public List<HistoryPrice> getAllHistoryPrice() {
        return historyPriceRepository.findAll();
    }

    @Override
    public List<HistoryPrice> getHistoryPriceByProduct(Integer productId) {
        return historyPriceRepository.findAllByProductId(productId);
    }
}
