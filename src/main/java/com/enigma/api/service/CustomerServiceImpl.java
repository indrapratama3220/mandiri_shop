package com.enigma.api.service;

import com.enigma.api.dto.CustomerSearchDTO;
import com.enigma.api.entity.Customer;
import com.enigma.api.exception.DataNotFoundException;
import com.enigma.api.repository.CustomerRepository;
import com.enigma.api.spesification.CustomerSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String id) {
        if(!customerRepository.existsById(id)){
            String message = String.format(DataNotFoundException.NOT_FOUND_MESSAGE, "customer" , id);
            throw new DataNotFoundException(message);
        }
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpesification.getSpesification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public Customer editCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomerByName(String nameCriterias, String email) {
        return customerRepository.findCustomerByFirstNameContainingAndEmailContaining(nameCriterias, email);
//        return customerRepository.findCustomerByFirstNameIsLikeAndEmailIsLike("%"+nameCriterias+"%", "%"+email+"%");
    }

    public List<Customer> sortAsc(){
        return customerRepository.findByOrderByFirstNameDesc();
    }
}
