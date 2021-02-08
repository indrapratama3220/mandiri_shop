package com.enigma.api.controller;

import com.enigma.api.entity.Product;
import com.enigma.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
}
