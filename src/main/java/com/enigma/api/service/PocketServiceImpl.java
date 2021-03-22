package com.enigma.api.service;

import com.enigma.api.entity.Pocket;
import com.enigma.api.exception.DataNotFoundException;
import com.enigma.api.repository.CustomerRepository;
import com.enigma.api.repository.PocketRepository;
import com.enigma.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketServiceImpl implements PocketService {

    @Autowired
    PocketRepository pocketRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Pocket getPocketById(String id) {
        return pocketRepository.findById(id).get();
    }

    @Override
    public Pocket savePocket(Pocket pocket) {
        if(!customerRepository.existsById(pocket.getCustomer().getId())){
            String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "customer" , pocket.getCustomer().getId());
            throw new DataNotFoundException(message);
        }
//        if(!productRepository.existsById(String.valueOf(pocket.getProduct().getId()))){
//            String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "product" , pocket.getProduct().getId());
//            throw new DataNotFoundException(message);
//        }
        return pocketRepository.save(pocket);
    }

    @Override
    public Pocket editPocket(Pocket pocket) {
        return pocketRepository.save(pocket);
    }

    @Override
    public void deletePocket(String id) {
        pocketRepository.deleteById(id);
    }
}
