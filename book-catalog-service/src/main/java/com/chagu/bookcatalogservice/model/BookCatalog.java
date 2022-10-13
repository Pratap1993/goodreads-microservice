package com.chagu.bookcatalogservice.model;

public class BookCatalog {

    private BookInfo bookInfo;

    private BookRating bookRating;

    public BookCatalog() {
    }

    public BookCatalog(BookInfo bookInfo, BookRating bookRating) {
        this.bookInfo = bookInfo;
        this.bookRating = bookRating;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public BookRating getBookRating() {
        return bookRating;
    }

    public void setBookRating(BookRating bookRating) {
        this.bookRating = bookRating;
    }
}
