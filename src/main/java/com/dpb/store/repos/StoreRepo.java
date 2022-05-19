package com.dpb.store.repos;

import com.dpb.store.entites.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepo extends CrudRepository<Store, Integer> {
}
