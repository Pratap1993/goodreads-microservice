package com.chagu.bookcatalogservice.controller;

import com.chagu.bookcatalogservice.model.BookCatalog;
import com.chagu.bookcatalogservice.model.BookInfo;
import com.chagu.bookcatalogservice.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/book-info")
    public List<BookInfo> getAllBookInfo() {
        return catalogService.getAllBookInfo();
    }

    @GetMapping("/book-info/{bookId}")
    public BookInfo getBookInfoByBookId(@PathVariable("bookId") String bookId) {
        return catalogService.getBookInfoByBookId(bookId);
    }

    @GetMapping("/book-catalog/")
    public List<BookCatalog> getAllBookCatalog() {
        return catalogService.getAllBookCatalog();
    }

    @GetMapping("/book-catalog/book-name/{bookName}")
    public BookCatalog getBookCatalogByName(@PathVariable("bookName") String bookName) {
        return catalogService.getBookCatalogByName(bookName);
    }

    @GetMapping("/book-catalog/book-rated/{ratings}")
    public List<BookCatalog> getAllBookCatalogByRating(@PathVariable("ratings") Integer ratings) {
        return catalogService.getAllBookCatalogByRating(ratings);
    }


}
