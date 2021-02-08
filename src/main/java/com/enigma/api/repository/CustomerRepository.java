package com.enigma.api.repository;

import com.enigma.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    public List<Customer> findCustomerByFirstNameContainingAndEmailContaining(String name, String email);
    public List<Customer> findCustomerByFirstNameIsLikeAndEmailIsLike(String name, String Email);
    List<Customer> findByOrderByFirstNameDesc();
}
