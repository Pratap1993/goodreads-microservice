package com.chagu.bookinfoservice.repository;

import com.chagu.bookinfoservice.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
}
