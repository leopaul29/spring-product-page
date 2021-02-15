package com.leopaul29.productpage;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Product("todo1", "description 1", 20.0));
        repository.save(new Product("todo2", "description 2", 4.50));

        // fetch all todo
        System.out.println("Product found with findAll():");
        System.out.println("-------------------------------");
        for (Product product : repository.findAll()) {
            System.out.println(product);
        }
        System.out.println();
    }

}
