package com.leopaul29.productpage.controller;

import com.leopaul29.productpage.data.Product;
import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public String greeting(Model model) {
        List<Product> productList = repository.findAll();
        model.addAttribute("productList", productList);
        return "home";
    }
}
