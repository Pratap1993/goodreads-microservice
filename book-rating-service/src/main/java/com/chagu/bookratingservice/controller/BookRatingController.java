package com.chagu.bookratingservice.controller;

import com.chagu.bookratingservice.model.BookRating;
import com.chagu.bookratingservice.service.BookRatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class BookRatingController {

    private final BookRatingService bookRatingService;

    public BookRatingController(BookRatingService bookRatingService) {
        this.bookRatingService = bookRatingService;
    }

    @GetMapping("/")
    public List<BookRating> getAllRatings() {
        return bookRatingService.getAllRatings();
    }

    @GetMapping("/{bookId}")
    public BookRating getByMovieId(@PathVariable("bookId") String bookId) {
        return bookRatingService.getByBookId(bookId);
    }

    @GetMapping("/rated/{ratedNumber}")
    public List<BookRating> getByRatedNumber(@PathVariable("ratedNumber") Integer ratedNumber) {
        return bookRatingService.getByUserRating(ratedNumber);
    }
}
