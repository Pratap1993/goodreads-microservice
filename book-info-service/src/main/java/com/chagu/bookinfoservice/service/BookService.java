package com.chagu.bookinfoservice.service;

import com.chagu.bookinfoservice.model.Book;
import com.chagu.bookinfoservice.repository.BookAuthorRepository;
import com.chagu.bookinfoservice.repository.BookRepository;
import com.chagu.bookinfoservice.repository.CustomRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookAuthorRepository bookAuthorRepository;

    private final CustomRepository customRepository;

    public BookService(BookRepository bookRepository, BookAuthorRepository bookAuthorRepository, CustomRepository customRepository) {
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
        this.customRepository = customRepository;
    }

    public Book getById(Integer bookId) {
        List<Integer> sampleIds = Arrays.asList(1, 3, 7);
        customRepository.getBookListByIds(sampleIds).forEach(book -> System.out.println(book.getBookId() + " " + book.getBookName()));
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        return bookOpt.orElse(null);
    }

    public Book getByName(String bookName) {
        Optional<Book> bookOpt = bookRepository.findByBookName(bookName);
        return bookOpt.orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByIds(String bookIds) {
        if (Objects.isNull(bookIds))
            return null;
        String ids = bookIds.replaceAll("\\s", "").replace("[", "").replace("]", "");
        List<Integer> bookIdList = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return customRepository.getBookListByIds(bookIdList);
    }
}
