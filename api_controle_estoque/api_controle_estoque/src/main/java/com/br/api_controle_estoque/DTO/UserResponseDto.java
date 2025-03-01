package com.br.api_controle_estoque.DTO;

import com.br.api_controle_estoque.model.Product;
import com.br.api_controle_estoque.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profile;
    private List<String> productNames;

    public UserResponseDto(Long id, String name, String email, String profile, List<String> productNames) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.productNames = productNames;
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.productNames = user.getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getProfile(),
                user.getProducts() != null
                        ? user.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.toList())
                        : null
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }
}
