package com.dpb.store.repos;

import com.dpb.store.entites.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Integer> {
}
