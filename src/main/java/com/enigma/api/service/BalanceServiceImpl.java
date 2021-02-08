package com.enigma.api.service;

import com.enigma.api.entity.Balance;
import com.enigma.api.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public Balance getBalanceById(String id) {
        return balanceRepository.findById(id).get();
    }

    @Override
    public Balance saveBalance(Balance balance) {
        return balanceRepository.save(balance);
    }
}
