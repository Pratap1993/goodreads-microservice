package com.chagu.bookcatalogservice.model;

import java.util.List;

public class BookIds {
    private List<String> bookIds;

    public BookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }
}
