package com.leopaul29.productpage.controller;

import com.leopaul29.productpage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }
}
