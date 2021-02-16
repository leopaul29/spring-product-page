package com.leopaul29.productpage;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ProductRepository repository;

    public void run(String... args) throws Exception {
        repository.deleteAll();
        Product product = new Product("product1", "description 1", 20.0);
        repository.save(product);

        // fetch all todo
        System.out.println("Product found with findAll():");
        System.out.println("-------------------------------");
        for (Product productItem : repository.findAll()) {
            System.out.println(productItem);
        }
        System.out.println();

        // Update
        product = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is("product1")), Product.class);
        product.setName("new product");
        mongoTemplate.save(product, "product");

        // Find One
        Product p = repository.findById(product.getId()).get();
        System.out.println(p);


        repository.save(new Product("product2", "description 2", 22.0));
        repository.save(new Product("product3", "description 3", 23.0));
        repository.save(new Product("product4", "description 4", 24.0));

        // Page all
        Pageable pageableRequest = PageRequest.of(0, 2);
        Page<Product> pages = repository.findAll(pageableRequest);
        List<Product> products = pages.getContent();
        System.out.println(products);
    }

}
