package com.project.web.kortezovart.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title can not be blank!")
    private String title;

    @Column(length = 6000)
    private String description;

    @NotNull(message = "price can not be empty!")
    @PositiveOrZero(message = "price must be 0 or positive")
    private BigDecimal price;

    private String mainImage;

    @ElementCollection
    private List<String> gallery;

    public Product() {

    }

    public Product(int id, String title, String description, BigDecimal price, String mainImage) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPrice(price);
        setMainImage(mainImage);
        gallery = new ArrayList<>();
    }

    public Product(String title, String description, BigDecimal price, String mainImage) {

        setTitle(title);
        setDescription(description);
        setPrice(price);
        setMainImage(mainImage);
        gallery = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
}
