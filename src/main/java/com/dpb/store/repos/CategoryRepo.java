package com.dpb.store.repos;

import com.dpb.store.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    List<Category> findAllByName(String name);

    Category findOneByName(String name);

    List<Category> findByParent(Integer i);
}
