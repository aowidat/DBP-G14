package com.dpb.store.repos;

import com.dpb.store.entites.Store;
import com.dpb.store.entites.Store_Id;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepo extends CrudRepository<Store, Integer> {
}
