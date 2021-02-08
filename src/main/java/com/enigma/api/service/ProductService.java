package com.enigma.api.service;

import com.enigma.api.entity.Product;

public interface ProductService {
    public Product getProductById(String id);
    public Product saveProduct(Product product);
}
