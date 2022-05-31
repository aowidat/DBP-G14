package com.dpb.store.repos;

import com.dpb.store.entites.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepo extends JpaRepository<Store, Integer> {
}
