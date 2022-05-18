package com.dpb.store.repos;

import com.dpb.store.entites.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
