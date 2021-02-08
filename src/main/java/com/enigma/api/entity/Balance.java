package com.enigma.api.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_balance")
public class Balance {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "balance_id")
    private String id;
    @Column(name = "balance_qty")
    private Double balanceQty;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Balance() {
    }

    public Balance(Double balanceQty) {
        this.balanceQty = balanceQty;
    }

    public Balance(double v, Customer customer) {
        this.balanceQty =v;
        this.customer=customer;
    }

    public Balance(Double balanceQty, Customer customer, Product product) {
        this.balanceQty = balanceQty;
        this.customer = customer;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(Double balanceQty) {
        this.balanceQty = balanceQty;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
