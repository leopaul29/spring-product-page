package com.leopaul29.productpage.repository;

import com.leopaul29.productpage.data.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
