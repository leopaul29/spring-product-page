package com.leopaul29.productpage.data;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
