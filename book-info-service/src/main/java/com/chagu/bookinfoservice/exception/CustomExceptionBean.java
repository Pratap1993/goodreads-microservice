package com.chagu.bookinfoservice.exception;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CustomExceptionBean {

    @NotNull
    private LocalDateTime timeStamp;

    @NotNull
    private String message;

    public CustomExceptionBean(LocalDateTime timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
