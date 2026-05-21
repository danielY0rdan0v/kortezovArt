package com.project.web.kortezovart.service;

import com.project.web.kortezovart.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product findById(int id);

    Product findByName(String name);

    Product updateProduct(int id, Product product, MultipartFile image, List<MultipartFile> galleryFiles, List<String> existingGallery);

    void deleteById(int id);

    Product createProduct(Product product, MultipartFile image, List<MultipartFile> galleryFiles);
}
