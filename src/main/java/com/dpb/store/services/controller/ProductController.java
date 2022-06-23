package com.dpb.store.services.controller;

import com.dpb.store.entites.Product;
import com.dpb.store.repos.ProductRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/product/{id}")
    Product getOne(@PathVariable String id) {
        return productRepo.findById(id).orElseThrow();
    }

    @GetMapping("/products/{pattern}")
    List<Product> getAllByPattern(@PathVariable String pattern) {
        return productRepo.findAllByTitle(pattern);
    }
}
