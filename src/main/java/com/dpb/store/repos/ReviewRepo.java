package com.dpb.store.repos;

import com.dpb.store.entites.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
