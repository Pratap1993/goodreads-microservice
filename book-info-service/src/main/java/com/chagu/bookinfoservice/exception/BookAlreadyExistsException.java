package com.chagu.bookinfoservice.exception;

import java.io.Serial;

public class BookAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 561441598983748689L;

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
