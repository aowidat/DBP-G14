package com.dpb.store.repos;

import com.dpb.store.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends JpaRepository<Book, String> {
}
