package com.dpb.store.repos;

import com.dpb.store.entites.SimiProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SimiRepo extends JpaRepository<SimiProduct, String> {
}
