package com.ecom.billingservice;

import com.ecom.billingservice.Entities.Bill;
import com.ecom.billingservice.Entities.ProductItem;
import com.ecom.billingservice.Models.Customer;
import com.ecom.billingservice.Models.Product;
import com.ecom.billingservice.Repository.BillRepository;
import com.ecom.billingservice.Repository.ProductItemRepository;
import com.ecom.billingservice.ServicesCommunication.CustomerRestClient;
import com.ecom.billingservice.ServicesCommunication.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProductItemRepository productItemRepository, BillRepository billRepository, CustomerRestClient customerRestClient , ProductRestClient productRestClient){
        return args -> {

            Collection<Product> products=productRestClient.allProducts().getContent();
            Long customerId = 1L;
            Customer customer = customerRestClient.findCustomerById(customerId);
            System.out.println(customer);
            if(customer==null){
                throw new RuntimeException("customer not found");
            }

            Bill bill = new Bill();
            bill.setCustomerId(customer.getId());
            bill.setBillDate(new Date());
            Bill billSave =billRepository.save(bill);
            products.forEach(p->{
                ProductItem item = new ProductItem();
                item.setBill(billSave);
                item.setQuantity(p.getQuantityStock());item.setPrice(1+new Random().nextInt(10));
                item.setProduct(p);
                item.setProductId(p.getId());
                item.setDiscount(Math.random());
                item.setPrice(p.getPrice());
                productItemRepository.save(item);


            });




        };
    }

}
