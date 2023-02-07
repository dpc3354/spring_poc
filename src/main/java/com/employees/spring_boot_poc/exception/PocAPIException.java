package com.employees.spring_boot_poc.exception;

import org.springframework.http.HttpStatus;

public class PocAPIException  extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String message;

    public PocAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public PocAPIException(String message, HttpStatus httpStatus, String message2) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message2;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
