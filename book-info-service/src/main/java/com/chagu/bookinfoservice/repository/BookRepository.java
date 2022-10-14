package com.chagu.bookinfoservice.repository;

import com.chagu.bookinfoservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByBookName(String bookName);
}
