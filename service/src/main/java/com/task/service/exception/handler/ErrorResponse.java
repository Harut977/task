package com.task.service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ErrorResponse class represents the error code of the exception
 *
 * @author Harut
 * @since 12.17.2020
 */
@Getter
@Setter
@Data
@AllArgsConstructor
public class ErrorResponse {

    /**
     * errors, which is a List of ErrorFieldResponses
     */
    private List<ErrorFieldResponse> errors;
}
