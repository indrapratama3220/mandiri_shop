package com.enigma.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_product")
public class Product {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id")
    private String id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_status")
    private Integer productStatus;
    @Column(name = "product_price_buy")
    private Integer productPriceBuy;
    @Column(name = "product_price_sell")
    private Integer productPriceSell;
    @Column(name = "product_image")
    private String productImage;

    public Product() {
    }

    public Product(String productName, Integer productStatus, Integer productPriceBuy, Integer productPriceSell, String productImage) {
        this.productName = productName;
        this.productStatus = productStatus;
        this.productPriceBuy = productPriceBuy;
        this.productPriceSell = productPriceSell;
        this.productImage = productImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getProductPriceBuy() {
        return productPriceBuy;
    }

    public void setProductPriceBuy(Integer productPriceBuy) {
        this.productPriceBuy = productPriceBuy;
    }

    public Integer getProductPriceSell() {
        return productPriceSell;
    }

    public void setProductPriceSell(Integer productPriceSell) {
        this.productPriceSell = productPriceSell;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
