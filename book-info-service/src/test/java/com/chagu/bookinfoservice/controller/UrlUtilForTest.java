package com.chagu.bookinfoservice.controller;

import java.util.Arrays;

public class UrlUtilForTest {

    private final int serverPort;
    private final String hostPath = "http://localhost:";

    public UrlUtilForTest(int serverPort) {
        this.serverPort = serverPort;
    }

    private String getBaseUrl() {
        return hostPath + serverPort;
    }

    private String getBookBaseUrl() {
        return getBaseUrl() + "/book";
    }

    public String getSaveBookUrl() {
        return getBookBaseUrl() + "/save-book";
    }

    public String getAllBooksUrl() {
        return getBookBaseUrl() + "/";
    }

    public String getBooksByIdUrl(int id) {
        return getBookBaseUrl() + "/" + id;
    }

    public String getBookByBookNameUrl(String bookName) {
        return getBookBaseUrl() + "/book-name/" + bookName;
    }

    public String getBookByBookIdsUrl(String[] bookIds) {
        return getBookBaseUrl() + "/book-ids/" + Arrays.toString(bookIds);
    }
}
