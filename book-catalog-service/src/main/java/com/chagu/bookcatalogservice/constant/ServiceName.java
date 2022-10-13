package com.chagu.bookcatalogservice.constant;

public enum ServiceName {

    BOOK_INFO_SERVICE("book-info-service"),
    BOOK_RATING_SERVICE("book-rating-service");

    private final String serviceName;

    ServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

}
