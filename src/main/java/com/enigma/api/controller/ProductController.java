package com.enigma.api.controller;

import com.enigma.api.constant.ApiUrlConstant;
import com.enigma.api.entity.Product;
import com.enigma.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> allProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/page")
    public Page<Product> productsPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer sizePerPage){
        Pageable pageable = PageRequest.of(page, sizePerPage);
        return productService.getProductsByPage(pageable);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam(name = "id") String id){
        productService.deleteProduct(id);
    }

    @PutMapping
    public Product editProduct(@RequestBody Product product){
        return productService.editProduct(product);
    }
}
