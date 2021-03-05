package com.enigma.api.controller;

import com.enigma.api.constant.ApiUrlConstant;
import com.enigma.api.dto.ProductSearchDTO;
import com.enigma.api.entity.Product;
import com.enigma.api.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> allProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/page")
    public Page<Product> productsPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "10") Integer sizePerPage,
                                         @RequestParam(name = "productName", required = false) String productName,
                                         @RequestParam(name = "sortBy", defaultValue = "productName") String sortBy,
                                         @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

        if (sortBy != null) {
            Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
            Pageable pageable = PageRequest.of(page, sizePerPage, sort);
            ProductSearchDTO productSearchDTO = new ProductSearchDTO(productName);
            return productService.getProductsByPage(pageable, productSearchDTO);
        } else {
            ProductSearchDTO productSearchDTO = new ProductSearchDTO(productName);
            Pageable pageable = PageRequest.of(page, sizePerPage);
            return productService.getProductsByPage(pageable, productSearchDTO);
        }
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam(name = "id") String id) {
        productService.deleteProduct(id);
    }

    @PutMapping
    public Product editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @PostMapping("/picture")
    public void registerProductPicture(@RequestPart(name = "picture", required = false) MultipartFile file,
                                       @RequestPart(name = "product") String product
                                       ){
        Product product1 = new Product();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product1 = objectMapper.readValue(product, Product.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.saveProductPicture(product1, file);
    }

    @PostMapping("/{id}/picture")
    public void uploadPicture(@PathVariable(name = "id") String id,
                                       @RequestParam(name = "picture") MultipartFile file) {
        String pathFolderString = "E:\\Sylabus\\Java\\Mandiri\\handson\\mandiri_shop\\src\\image";
        Path pathFolder = Paths.get(pathFolderString + id);
        Path pathFile = Paths.get(pathFolder.toString() + "/" + id + ".png");
        try {
            Files.createDirectory(pathFolder);
            file.transferTo(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
