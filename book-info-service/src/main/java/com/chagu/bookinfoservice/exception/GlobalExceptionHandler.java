package com.chagu.bookinfoservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {BookAlreadyExistsException.class})
    public ResponseEntity<Object> handleBookAlreadyExistsException(BookAlreadyExistsException ex, WebRequest request) {
        CustomExceptionBean errorMessage = new CustomExceptionBean(LocalDateTime.now(), ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingFieldException.class})
    public ResponseEntity<Object> handleMissingFieldException(MissingFieldException ex, WebRequest request) {
        CustomExceptionBean errorMessage = new CustomExceptionBean(LocalDateTime.now(), ex.getMessage());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
