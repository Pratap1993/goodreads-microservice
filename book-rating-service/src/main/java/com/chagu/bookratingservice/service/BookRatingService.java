package com.chagu.bookratingservice.service;

import com.chagu.bookratingservice.model.BookRating;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookRatingService {
    private List<BookRating> ratingDataSource = null;

    public BookRatingService() {
        ratingDataSource = Arrays.asList(
                new BookRating("1", "Half Girlfriend is just a one time read", 3),
                new BookRating("2", "Pretty average story", 2),
                new BookRating("3", "A nice and engaging story", 4),
                new BookRating("4", "Awesome story, inspired from the author's own life.", 5),
                new BookRating("5", "A very good story", 3),
                new BookRating("6", "Not recommended at all, bad/difficult words to understand for a common man.", 1)
        );
    }

    public BookRating getByBookId(String bookId) {
        return ratingDataSource.stream().filter(bookRating -> bookRating.getBookId().equals(bookId))
                .findAny().orElse(null);
    }

    public List<BookRating> getAllRatings() {
        return ratingDataSource;
    }

    public List<BookRating> getByUserRating(Integer userRating) {
        return ratingDataSource.stream().filter(bookRating -> bookRating.getUserRating() >= userRating).toList();
    }
}