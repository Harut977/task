package com.task.service.exception.models;

import com.task.service.exception.handler.ErrorCode;
import com.task.service.exception.handler.ErrorFieldResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * This the base exception which is extended from the Runtime Exception
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    /**
     * Error code of the exception
     */
    private ErrorCode errorCode;

    /**
     * The List of Errors
     */
    private List<ErrorFieldResponse> errors;


    public BaseException(ErrorCode errorCode, List<ErrorFieldResponse> errors) {
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public BaseException(String message) {
        super(message);
    }
}
