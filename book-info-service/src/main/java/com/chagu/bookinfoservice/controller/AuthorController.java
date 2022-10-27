package com.chagu.bookinfoservice.controller;

import com.chagu.bookinfoservice.dto.response.AuthorDetailsResponse;
import com.chagu.bookinfoservice.service.BookAuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final BookAuthorService bookAuthorService;

    public AuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping(value = "/")
    public List<AuthorDetailsResponse> getAllAuthors() {
        return bookAuthorService.getAllAuthors();
    }

    @GetMapping("/author-name/{authorName}")
    public AuthorDetailsResponse getBookAuthorByName(@PathVariable("authorName") String authorName) {
        return bookAuthorService.getBookAuthorByName(authorName);
    }

    @GetMapping("/author-email/{authorEmail}")
    public AuthorDetailsResponse getBookAuthorByEmail(@PathVariable("authorEmail") String authorEmail) {
        return bookAuthorService.getBookAuthorByEmail(authorEmail);
    }
}
