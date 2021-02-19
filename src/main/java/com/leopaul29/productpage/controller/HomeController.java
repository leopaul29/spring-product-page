package com.leopaul29.productpage.controller;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> productList = repository.findAll();
        model.addAttribute("productList", productList);
        return "home";
    }

    @GetMapping(value = "/product/{id}")
    public String productPage(@PathVariable String id, Model model) {
        Optional<Product> optProduct = repository.findById(id);
        if(optProduct.isPresent()) {
            Product product = optProduct.get();
            model.addAttribute("product", product);
        }
        return "product";
    }
}
