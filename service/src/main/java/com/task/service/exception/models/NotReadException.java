package com.task.service.exception.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This is used for exceptions that are not read. It is extended from the RuntimeException.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Getter
@Setter
public class NotReadException extends RuntimeException {

    /**
     * the HttpStatus of the exception
     */
    private HttpStatus httpStatus;

    public NotReadException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
