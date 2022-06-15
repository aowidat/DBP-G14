package com.dpb.store.repos;

import com.dpb.store.entites.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
