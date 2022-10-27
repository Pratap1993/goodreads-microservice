package com.chagu.bookinfoservice.exception;

public enum ExceptionMessage {

    BOOK_ALREADY_EXISTS("Book is already present in good-reads."),
    MISSING_FIELD("Please provide {} value");

    private final String exceptionMessage;

    ExceptionMessage(final String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
