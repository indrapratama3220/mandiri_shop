package com.enigma.api.service;

import com.enigma.api.dto.ProductSearchDTO;
import com.enigma.api.entity.HistoryPrice;
import com.enigma.api.entity.Product;
import com.enigma.api.exception.DataNotFoundException;
import com.enigma.api.repository.HistoryPriceRepository;
import com.enigma.api.repository.ProductRepository;
import com.enigma.api.spesification.ProductSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    HistoryPriceRepository historyPriceRepository;

    @Override
    public Product getProductById(String id) {
        if(!productRepository.existsById(id)){
            String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "product" , id);
            throw new DataNotFoundException(message);
        }
        return productRepository.findById(id).get();
    }

    @Override
    public void editProduct(Product product) {

        Product product1 = productRepository.save(product);
        HistoryPrice historyPrice = new HistoryPrice();
        Date date = new Date();
        historyPrice.setDate(date);
        historyPrice.setPriceBuy(product.getProductPriceBuy());
        historyPrice.setPriceSell(product.getProductPriceSell());
        historyPrice.setProduct(product1);
        historyPriceRepository.save(historyPrice);
    }

    @Override
    public Product saveProductPicture(Product product, MultipartFile file) {
        System.out.println("INI FILE " + file);
        String pathFolderString = "E:\\Sylabus\\Java\\Mandiri\\handson\\mandiri_shop\\src\\image\\";
        Path pathFolder = Paths.get(pathFolderString + file.getOriginalFilename());
        Path pathFile = Paths.get(pathFolder.toString() + "/" + file.getOriginalFilename() + ".png");
        try {
            Files.createDirectory(pathFolder);
            file.transferTo(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setProductImage(file.getOriginalFilename());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductsByPage(Pageable pageable, ProductSearchDTO productSearchDTO) {
        Specification<Product> productSpecification = ProductSpesification.getSpesification(productSearchDTO);
        return productRepository.findAll(productSpecification, pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
