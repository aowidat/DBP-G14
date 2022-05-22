package com.dpb.store.repos;

import com.dpb.store.entites.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CDRepo extends JpaRepository<CD, String> {
}
