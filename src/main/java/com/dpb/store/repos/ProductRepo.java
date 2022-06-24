package com.dpb.store.repos;

import com.dpb.store.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {
    List<Product> findAllByTitle(String title);

    List<Product> findAllByRating(Double i);
}
