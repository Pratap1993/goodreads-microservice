package com.chagu.bookcatalogservice.constant;

import java.util.Arrays;

public abstract class BookInfoUrl {

    public static String getUrlForFindAllBookInfo() {
        return "/book/";
    }

    public static String getUrlForFindBookInfoByBookId(String bookId) {
        return "/book/" + bookId;
    }

    public static String getUrlForFindAllBookInfoByIds(String[] bookIds) {
        return "/book/book-ids/" + Arrays.toString(bookIds);
    }

    public static String getUrlForFindBookInfoByBookName(String bookName) {
        return "/book/book-name/" + bookName;
    }

}
