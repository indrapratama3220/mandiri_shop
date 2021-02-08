package com.enigma.api.service;

import com.enigma.api.entity.Balance;

public interface BalanceService {
    public Balance getBalanceById(String id);
    public Balance saveBalance(Balance balance);
}
