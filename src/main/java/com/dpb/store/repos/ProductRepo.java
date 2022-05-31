package com.dpb.store.repos;

import com.dpb.store.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
}
