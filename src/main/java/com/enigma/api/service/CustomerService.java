package com.enigma.api.service;

import com.enigma.api.dto.CustomerSearchDTO;
import com.enigma.api.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CustomerService {
public Customer getCustomerById(String id);
public Customer saveCustomer(Customer customer);
public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
public Customer editCustomer(Customer customer);
public void deleteCustomer(String id);
public List<Customer> getCustomerByName(String nameCriterias, String email);
    public List<Customer> sortAsc();
}
