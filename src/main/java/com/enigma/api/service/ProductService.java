package com.enigma.api.service;

import com.enigma.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Product getProductById(String id);
    public Product saveProduct(Product product);
    public List<Product> getAllProduct();
    public Page<Product> getProductsByPage(Pageable pageable);
    public Product editProduct(Product product);
    public void deleteProduct(String id);
}
