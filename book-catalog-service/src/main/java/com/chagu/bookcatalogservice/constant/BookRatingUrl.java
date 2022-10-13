package com.chagu.bookcatalogservice.constant;

public abstract class BookRatingUrl {

    public static String getUrlForFindAllBookRating() {
        return "/rating/";
    }

    public static String getUrlForFindBookRatingByBookId(String bookId) {
        return "/rating/" + bookId;
    }

    public static String getUrlForFindAllBookRatingByRating(Integer rating) {
        return "/rating/rated/" + rating;
    }

}
