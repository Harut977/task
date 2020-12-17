package com.task.service.exception.handler;

import org.springframework.http.HttpStatus;

/**
 * The ErrorCode represents the status of the error, which include the name and status for it
 *
 * @author Harut
 * @since 12.17.2020
 */
public interface ErrorCode {

    /**
     * getting the Status of the error
     *
     * @return the status of the Code as an HttpStatus
     */
    HttpStatus getStatus();

}
