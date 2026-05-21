package com.project.web.kortezovart.controllers;

import com.project.web.kortezovart.exceptions.EntityNotFoundException;
import com.project.web.kortezovart.models.Product;
import com.project.web.kortezovart.models.User;
import com.project.web.kortezovart.repositories.ProductRepository;
import com.project.web.kortezovart.repositories.UserRepository;
import com.project.web.kortezovart.service.FileStorageService;
import com.project.web.kortezovart.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public Product createProduct(
            @Valid @ModelAttribute Product product,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "galleryFiles", required = false) List<MultipartFile> galleryFiles) {


        return service.createProduct(product, image, galleryFiles);
    }

    @PutMapping("{id}")
    public Product updateProduct(
            @PathVariable int id,
            @Valid @ModelAttribute Product product,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "galleryFiles", required = false) List<MultipartFile> galleryFiles,
            @RequestParam(value = "existingGallery", required = false) List<String> existingGallery) {

        return service.updateProduct(id, product, image, galleryFiles, existingGallery);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {

        service.deleteById(id);
    }


}
