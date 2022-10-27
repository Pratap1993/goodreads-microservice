package com.chagu.bookinfoservice.controller;

import com.chagu.bookinfoservice.dto.request.BookRequest;
import com.chagu.bookinfoservice.dto.response.BookDetailsResponse;
import com.chagu.bookinfoservice.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<BookDetailsResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDetailsResponse getBookById(@PathVariable("bookId") Integer bookId) {
        return bookService.getById(bookId);
    }

    @GetMapping("/book-name/{bookName}")
    public BookDetailsResponse getBookByName(@PathVariable("bookName") String bookName) {
        return bookService.getByName(bookName);
    }

    @GetMapping("/book-ids/{bookIds}")
    public List<BookDetailsResponse> getBookByIds(@PathVariable("bookIds") String bookIds) {
        return bookService.getAllBooksByIds(bookIds);
    }

    @PostMapping("/save-book")
    public BookDetailsResponse saveBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookService.saveBook(bookRequest);
    }
}
