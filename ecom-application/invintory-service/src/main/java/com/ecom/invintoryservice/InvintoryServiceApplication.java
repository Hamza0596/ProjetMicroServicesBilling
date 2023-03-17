package com.ecom.invintoryservice;

import com.ecom.invintoryservice.Entities.Product;
import com.ecom.invintoryservice.Repository.ProducRepository;
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
public class InvintoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvintoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProducRepository productRepo, RepositoryRestConfiguration restConfiguration ){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            List<Product> prods = new ArrayList<>();
            prods.add(Product.builder().name("Iphone").price(100).quantityStock(30).build());
            prods.add(Product.builder().name("Samsung").price(500).quantityStock(38).build());
            prods.add(Product.builder().name("Redmi").price(93).quantityStock(31).build());

            productRepo.saveAll(prods);
            productRepo.findAll().forEach(p->System.out.println(p));



        };
    }

}
