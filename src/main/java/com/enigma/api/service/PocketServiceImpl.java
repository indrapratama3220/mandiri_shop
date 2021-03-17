package com.enigma.api.service;

import com.enigma.api.entity.Pocket;
import com.enigma.api.repository.PocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketServiceImpl implements PocketService {

    @Autowired
    PocketRepository pocketRepository;

    @Override
    public Pocket getPocketById(String id) {
        return pocketRepository.findById(id).get();
    }

    @Override
    public Pocket savePocket(Pocket balance) {
        return pocketRepository.save(balance);
    }
}
