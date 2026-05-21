package com.project.web.kortezovart.service;

import com.project.web.kortezovart.exceptions.EntityNotFoundException;
import com.project.web.kortezovart.exceptions.UnAuthorizedException;
import com.project.web.kortezovart.models.Product;
import com.project.web.kortezovart.models.Role;
import com.project.web.kortezovart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final FileStorageService  fileStorageService;

    @Autowired
    public ProductServiceImpl(ProductRepository repository,  FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product findById(int id) {
        return getById(id);
    }

    @Override
    public Product findByName(String name) {
        return getByTitle(name);
    }

    @Override
    public Product updateProduct(int id, Product product, MultipartFile image, List<MultipartFile> galleryFiles, List<String> existingGallery) {

        Product updatedProduct = getById(id);

        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        if (image != null &&!image.isEmpty()) {
            updatedProduct.setMainImage(fileStorageService.saveFile(image));
        }

        List<String> updatedGallery = new ArrayList<>();

        if(existingGallery != null){
            updatedGallery.addAll(existingGallery);
        }

        if (galleryFiles != null &&!galleryFiles.isEmpty()) {
            for (MultipartFile file : galleryFiles) {
                if (!file.isEmpty()) {
                    updatedGallery.add(fileStorageService.saveFile(file));
                }
            }
        }

        updatedProduct.setGallery(updatedGallery);
        repository.save(updatedProduct);
        return updatedProduct;
    }

    @Override
    public void deleteById(int id) {

        repository.deleteById(id);

    }

    @Override
    public Product createProduct(Product product, MultipartFile image, List<MultipartFile> galleryFiles) {
        if (image!=null && !image.isEmpty()) {
            String imagePath = fileStorageService.saveFile(image);
            product.setMainImage(imagePath);
        }

        if (galleryFiles!=null && !galleryFiles.isEmpty()) {
            List<String> galleryPaths = new ArrayList<>();

            for(MultipartFile file : galleryFiles) {
                if(!file.isEmpty()) {
                    String filePath = fileStorageService.saveFile(file);
                    galleryPaths.add(filePath);
                }
            }
            product.setGallery(galleryPaths);
        }
        return repository.save(product);
    }

    public Product getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("product", "id", id)
        );
    }

    public Product getByTitle(String title) {
        return repository.findByTitleIgnoreCase(title)
                .orElseThrow(
                        () -> new EntityNotFoundException("product", "title", title)
                );


    }
}
