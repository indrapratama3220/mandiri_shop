package com.enigma.api.controller;

import com.enigma.api.constant.ApiUrlConstant;
import com.enigma.api.dto.CustomerSearchDTO;
import com.enigma.api.entity.Customer;
import com.enigma.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
    return customerService.saveCustomer(customer);
    }

    @GetMapping
    public Page<Customer> searchCustomerPerPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                @RequestParam(name = "size", defaultValue = "10") Integer sizePerPage,
                                                @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                                @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                @RequestParam(name = "firstName", required = false) String firstName,
                                                @RequestParam(name = "email", required = false) String email,
                                                @RequestParam(name = "address", required = false) String address,
                                                @RequestParam(name = "dateOfBirth", required = false) Date dateOfBirth){
        if(sortBy != null) {
            Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
            Pageable pageable = PageRequest.of(page, sizePerPage, sort);
            CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO(firstName, email, address, dateOfBirth);
            return customerService.getCustomerPerPage(pageable, customerSearchDTO);
        } else {
            Pageable pageable = PageRequest.of(page, sizePerPage);
            CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO(firstName, email, address, dateOfBirth);
            return customerService.getCustomerPerPage(pageable, customerSearchDTO);
        }

    }

    @GetMapping("/cust")
    public void coba(@RequestParam String [] names){
        for (String name:
             names) {
            System.out.println(name);
        }
    }

//    @GetMapping("/customers")
//    public List<Customer> searchCustomerByName(@RequestParam(name = "name", defaultValue = "") String name,
//                                               @RequestParam(name = "email", defaultValue = "") String email){
//        return customerService.getCustomerByName(name, email);
//    }

//    @GetMapping("/customers")
//    public List<Customer> searchCustomerByName(){
//        return customerService.sortAsc();
//    }

    @PutMapping
    public Customer editCustomer(@RequestBody Customer customer){
        return customerService.editCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam(name = "id") String id){
       customerService.deleteCustomer(id);
    }


}
