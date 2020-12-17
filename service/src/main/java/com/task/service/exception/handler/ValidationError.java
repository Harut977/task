package com.task.service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is used the status code of the error, it is implemented from the ErrorCode interface
 *
 * @author Harut
 * @since 12.17.2020
 */
@Getter
@AllArgsConstructor
public enum ValidationError implements ErrorCode {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST);

    private final HttpStatus status;

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
