package com.task.service.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * This is the Base response of exception, which shows, its' success, message and the data.
 *
 * @param <T> the type of the data
 * @author Harut
 * @since 12.17.2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseExceptionResponse<T> {

    private Boolean success;

    private String message;

    private T data;
}
