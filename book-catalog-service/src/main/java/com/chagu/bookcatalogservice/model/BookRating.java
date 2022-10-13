package com.chagu.bookcatalogservice.model;

public class BookRating {

    private String bookId;
    private String userRemarks;
    private Integer userRating;

    public BookRating() {
    }

    public BookRating(String bookId, String userRemarks, Integer userRating) {
        this.bookId = bookId;
        this.userRemarks = userRemarks;
        this.userRating = userRating;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserRemarks() {
        return userRemarks;
    }

    public void setUserRemarks(String userRemarks) {
        this.userRemarks = userRemarks;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }
}
