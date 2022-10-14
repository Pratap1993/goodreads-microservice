package com.chagu.bookinfoservice.controller;

import com.chagu.bookinfoservice.model.Book;
import com.chagu.bookinfoservice.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") Integer bookId) {
        return bookService.getById(bookId);
    }

    @GetMapping("/book-name/{bookName}")
    public Book getBookByName(@PathVariable("bookName") String bookName) {
        return bookService.getByName(bookName);
    }

    @GetMapping("/book-ids/{bookIds}")
    public List<Book> getBookByIds(@PathVariable("bookIds") String bookIds) {
        return bookService.getAllBooksByIds(bookIds);
    }
}
