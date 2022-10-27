package com.chagu.bookinfoservice.repository;

import com.chagu.bookinfoservice.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {

    Optional<BookAuthor> findByAuthorName(String bookAuthorName);
    Optional<BookAuthor> findByAuthorEmail(String bookAuthorEmail);
}
