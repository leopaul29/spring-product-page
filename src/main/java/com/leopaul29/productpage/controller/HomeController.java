package com.leopaul29.productpage.controller;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private ProductRepository repository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model) {
        Optional<Product> byId = repository.findById(id);
        if(byId.isPresent()) {
            Product product = byId.get();
            model.addAttribute("product", product);
        }
        return "product";
    }
}
