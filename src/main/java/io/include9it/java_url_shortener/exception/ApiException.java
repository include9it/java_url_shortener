package io.include9it.java_url_shortener.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiException(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime timestamp
) {
    public ApiException(HttpStatus httpStatus, ZonedDateTime timestamp) {
        this(null, httpStatus, timestamp);
    }
}
