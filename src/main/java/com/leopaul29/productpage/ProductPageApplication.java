package com.leopaul29.productpage;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductPageApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ProductPageApplication.class, args);
	}

	public void run(String... args) throws Exception {
		repository.deleteAll();

		repository.save(new Product("todo1", "description 1", 20.0));
		repository.save(new Product("todo2", "description 2", 4.50));

		// fetch all todo
		System.out.println("Todos found with findAll():");
		System.out.println("-------------------------------");
		for (Product product : repository.findAll()) {
			System.out.println(product);
		}
		System.out.println();
	}
}
