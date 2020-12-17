package com.task.api.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response is all the responses, through which is put the data here, it's success
 * and a message for the response.
 *
 * @param <T> represents the type of a response which is given to the class
 * @author Harut
 * @since 12.17.2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private boolean success;

    private String message;

    private T date;
}
