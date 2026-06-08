package com.project.web.kortezovart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping({"/", "/home"})
    public String index(){
        return "index.html";
    }

    @GetMapping("/products")
    public String products() {
        return "products.html";
    }

    @GetMapping("/product-details")
    public String productDetails() {
        return "product-details.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about-me.html";
    }
    @GetMapping("/contact")
    public String contactPage() {
        return "contact.html";
    }
}
