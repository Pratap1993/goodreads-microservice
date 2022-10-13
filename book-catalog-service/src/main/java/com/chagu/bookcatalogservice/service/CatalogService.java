package com.chagu.bookcatalogservice.service;

import com.chagu.bookcatalogservice.client.BookInfoClient;
import com.chagu.bookcatalogservice.client.BookRatingClient;
import com.chagu.bookcatalogservice.model.BookCatalog;
import com.chagu.bookcatalogservice.model.BookInfo;
import com.chagu.bookcatalogservice.model.BookRating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("catalogService")
public class CatalogService {

    private final BookInfoClient bookInfoClient;

    private final BookRatingClient bookRatingClient;

    public CatalogService(BookInfoClient bookInfoClient, BookRatingClient bookRatingClient) {
        this.bookInfoClient = bookInfoClient;
        this.bookRatingClient = bookRatingClient;
    }

    public List<BookInfo> getAllBookInfo() {
        return bookInfoClient.findAllBookInfo();
    }

    public List<BookRating> getAllBookRatings() {
        return bookRatingClient.findAllBookRatings();
    }

    public BookInfo getBookInfoByBookId(String bookId) {
        return bookInfoClient.findByBookId(bookId);
    }

    public List<BookCatalog> getAllBookCatalog() {
        List<BookInfo> bookInfoList = getAllBookInfo();
        List<BookRating> bookRatings = getAllBookRatings();
        return buildBookCatalogList(bookInfoList, bookRatings);
    }

    public BookCatalog getBookCatalogByName(String bookName) {
        BookInfo bookInfo = bookInfoClient.findByBookName(bookName);
        if (Objects.nonNull(bookInfo)) {
            BookRating bookRating = bookRatingClient.findByBookId(bookInfo.getBookId());
            return new BookCatalog(bookInfo, bookRating);
        }
        return null;
    }

    public List<BookCatalog> getAllBookCatalogByRating(Integer rating) {
        List<BookRating> bookRatings = bookRatingClient.getBookRatingsByRating(rating);
        String[] bookIds = bookRatings.stream().filter(Objects::nonNull).map(BookRating::getBookId).toArray(String[]::new);
        List<BookInfo> bookInfoList = bookInfoClient.findAllBookInfoByIds(bookIds);
        return buildBookCatalogList(bookInfoList, bookRatings);
    }

    private List<BookCatalog> buildBookCatalogList(List<BookInfo> bookInfoList, List<BookRating> bookRatingList) {
        return bookInfoList.stream().filter(Objects::nonNull).map(bookInfo -> {
            BookRating bookRating = bookRatingList.stream().filter(rate -> rate.getBookId().equals(bookInfo.getBookId()))
                    .findAny().orElse(null);
            return new BookCatalog(bookInfo, bookRating);
        }).toList();
    }
}
