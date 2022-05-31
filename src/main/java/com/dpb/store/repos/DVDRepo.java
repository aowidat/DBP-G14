package com.dpb.store.repos;

import com.dpb.store.entites.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DVDRepo extends JpaRepository<DVD, String> {
}
