package com.ecom.custumorservice;

import com.ecom.custumorservice.Entities.Customer;
import com.ecom.custumorservice.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient

public class CustumorServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustumorServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository custumorRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);

            List <Customer> costs = new ArrayList<>();
            costs.add(Customer.builder().name("Hassan").email("hassan@gmail.com").build());
            costs.add(Customer.builder().name("Hamza").email("hamzabouachir@yahoo.com").build());
            costs.add(Customer.builder().name("Hassan").email("hamza.bouachir@talan.com").build());

            custumorRepository.saveAll(costs);
            custumorRepository.findAll().forEach(c->System.out.println(c));



        };
    }
}
