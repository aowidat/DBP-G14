package com.dpb.store.repos;

import com.dpb.store.entites.DVD;
import org.springframework.data.repository.CrudRepository;

public interface DVDRepo extends CrudRepository<DVD, String> {
}
