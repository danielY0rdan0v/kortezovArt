package com.project.web.kortezovart.repositories;

import com.project.web.kortezovart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByTitleIgnoreCase(String title);
}
