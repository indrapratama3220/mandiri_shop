package com.enigma.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MandiriShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MandiriShopApplication.class, args);
    }

    @Bean
    public RestTemplate getResTemplate(){
        return new RestTemplate();
    }

}
