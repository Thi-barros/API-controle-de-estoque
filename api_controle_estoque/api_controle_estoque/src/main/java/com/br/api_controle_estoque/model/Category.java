package com.br.api_controle_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name of the category is required!")
    private String name;
    @NotEmpty(message = "The description of the category is required!")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "The name of the category is required!") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "The name of the category is required!") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "The description of the category is required!") String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty(message = "The description of the category is required!") String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
