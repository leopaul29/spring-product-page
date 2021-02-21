package com.leopaul29.productpage;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

   /* @Autowired
    private MongoTemplate mongoTemplate;/*
   */ @Autowired
    private ProductRepository repository;

    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new Product("product1", "description 1", 21.1));
        repository.save(new Product("product2", "description 2", 22.2));
        repository.save(new Product("product3", "description 3", 23.3));
        repository.save(new Product("product4", "description 4", 24.4));
        repository.save(new Product("product5", "description 5", 25.5));
    }

}
