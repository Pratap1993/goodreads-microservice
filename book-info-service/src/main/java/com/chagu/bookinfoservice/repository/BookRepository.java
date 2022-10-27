package com.chagu.bookinfoservice.repository;

import com.chagu.bookinfoservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByBookName(String bookName);

   /* @Modifying
    @Query("update Book b set b. = ?1, u.lastname = ?2 where u.id = ?3")
    void setUserInfoById(String firstname, String lastname, Integer userId);*/
}
