package com.dpb.store.repos;

import com.dpb.store.entites.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, String> {
}
