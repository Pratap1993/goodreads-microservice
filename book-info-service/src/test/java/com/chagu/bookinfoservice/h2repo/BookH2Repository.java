package com.chagu.bookinfoservice.h2repo;

import com.chagu.bookinfoservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookH2Repository extends JpaRepository<Book, Integer> {

    Optional<Book> findByBookName(String bookName);
}
