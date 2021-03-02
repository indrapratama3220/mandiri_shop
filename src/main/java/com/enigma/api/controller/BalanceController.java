package com.enigma.api.controller;

import com.enigma.api.entity.Balance;
import com.enigma.api.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping("/balances/{id}")
    public Balance getBalanceById(@PathVariable String id){
        return balanceService.getBalanceById(id);
    }

    @PostMapping("/balances")
    public Balance saveBalance(@RequestBody Balance balance){
        return balanceService.saveBalance(balance);
    }

}
