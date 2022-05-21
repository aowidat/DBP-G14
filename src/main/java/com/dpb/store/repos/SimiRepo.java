package com.dpb.store.repos;

import com.dpb.store.entites.SimiProduct;
import org.springframework.data.repository.CrudRepository;

public interface SimiRepo extends CrudRepository<SimiProduct, String> {
}
