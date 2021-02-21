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
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vestibulum auctor ante vitae porttitor. Vivamus porta malesuada condimentum.";
        for (int i = 0; i < 100; i++) {
            double randomPrice = Math.round(Math.random() * 1000) + Math.round(Math.random());
            int length = (int) Math.round(Math.random() * loremIpsum.length());
            if (length < 20) length = 20;
            String description = loremIpsum.substring(0, length);
            repository.save(new Product("product " + i, description, randomPrice));
        }
    }
}
