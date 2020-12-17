package com.task.service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorFieldResponse is the errors inside the requests' fields in the API
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorFieldResponse {

    /**
     * the field name of the error, which represents a String
     */
    private String field;

    /**
     * the message for the error, which represents a String
     */
    private String message;
}
