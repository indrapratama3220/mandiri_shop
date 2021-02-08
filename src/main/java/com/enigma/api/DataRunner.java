package com.enigma.api;

//import com.enigma.api.config.AppConfig;
import com.enigma.api.entity.Balance;
import com.enigma.api.entity.Customer;
import com.enigma.api.entity.Product;
import com.enigma.api.entity.Store;
import com.enigma.api.repository.BalanceRepository;
import com.enigma.api.repository.CustomerRepository;
import com.enigma.api.repository.ProductRepository;
import com.enigma.api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class DataRunner implements CommandLineRunner {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;



    @Override
    public void run(String... args) throws Exception {

        String uri = "http://localhost:8081/store/8a80cb8175f0cd600175f2c3d9b40002";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        System.out.println(responseEntity);
//        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01");
//        Customer customer = new Customer("Tika", "Raditya", birthDate, "Lampung", 1, "tika","abc", "tika@gmail.com");
//        customerRepository.save(customer);
//        Balance balance = new Balance(2.0, customer);
//        balanceRepository.save(balance);


//        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01");
//        Customer customer = new Customer("Angga", "Raditya", birthDate, "Lampung", 1, "tika","abc", "tika@gmail.com");
//        Balance balance = new Balance(2.0);
//        customer.addBalances(balance);
//        customerRepository.save(customer);

//        Optional<Customer> optionCust = customerRepository.findById("8a80cb817761a360017761a36b110000");
//        //null save = pengamanan sari null pointer exception
//        if (optionCust.isPresent()){
//            Customer cust = optionCust.get();
//            cust.removeBalance("8a80cb817761ecbf017761eebd6a0000");
//            customerRepository.save(cust);
//        }

//        Optional<Balance> optionCust = balanceRepository.findById("8a80cb817761fb71017761fb78950001");
//        if (optionCust.isPresent()){
//            Balance balance = optionCust.get();
//            Customer cust = balance.getCustomer();
//            balance.setBalanceQty(3.0);
//            customerRepository.save(cust);
//        }

//        customerRepository.deleteById("8a80cb817761a360017761a36b110000");

//        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01");
//        Customer customer = new Customer("Tika", "Raditya", birthDate, "Lampung", 1, "tika","abc", "tika@gmail.com");
//        Product product = new Product("Shampoo", 1, 2000, 2500, "Shampoo Image");
//        Balance balance = new Balance(2.0, customer, product);
//
//        customerRepository.save(customer);
//        productRepository.save(product);
//        balanceRepository.save(balance);
//
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        StoreService storeService = annotationConfigApplicationContext.getBean(StoreService.class);
//        for (Store s:
//             storeService.getAllStore()) {
//            System.out.println(s);
//        }
//
//        annotationConfigApplicationContext.close();



    }
}
