package com.ecom.billingservice.Models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private  double price;
    private int quantityStock;
}

