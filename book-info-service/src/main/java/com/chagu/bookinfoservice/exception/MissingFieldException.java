package com.chagu.bookinfoservice.exception;

import java.io.Serial;

public class MissingFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2330845810827556893L;

    public MissingFieldException(String message) {
        super(message);
    }
}
