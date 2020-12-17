package com.task.service.exception.handler;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.task.service.exception.models.BaseException;
import com.task.service.exception.models.BaseExceptionResponse;
import com.task.service.exception.models.NotReadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for all the exceptions which should be handled
 *
 * @author Harut
 * @since 12.17.2020
 */
@RestControllerAdvice
public class ExceptionHandlingController {

    /**
     * method which is responsible for the exceptions thrown in methods
     *
     * @param ex to be passed to the method
     * @return the ErrorResponse which includes Errors' List with their fields
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseExceptionResponse<ErrorResponse>> handleBaseException(BaseException ex) {
        List<ErrorFieldResponse> errors = ex.getErrors();
        if (errors == null) {
            errors = new ArrayList<>();
            errors.add(new ErrorFieldResponse("RequestBody", ex.getMessage()));
        }
        HttpStatus httpStatus;
        if (ex.getErrorCode() == null || ex.getErrorCode().getStatus() == null) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            httpStatus = ex.getErrorCode().getStatus();
        }
        BaseExceptionResponse<ErrorResponse> baseResponse =
                new BaseExceptionResponse<>(
                        false,
                        "Validation error.Please check errors object.",
                        new ErrorResponse(errors));
        return new ResponseEntity<>(
                baseResponse,
                httpStatus);
    }

    /**
     * this method is used when there is some not corresponding fields, it will throw an exception
     *
     * @param ex being passed to the method
     * @return BaseExceptionResponse different object depending on states
     */
    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<BaseExceptionResponse<Object>> handleFieldNotCorrectException(ValueInstantiationException ex) {
        try {
            Field requiredField = ex.getType().getRawClass().getDeclaredFields()[ex.getLocation().getColumnNr()];
            if (requiredField == null) {
                BaseExceptionResponse<Object> baseResponse = new BaseExceptionResponse<>(
                        false,
                        "Please set all corresponding fields: ".concat(ex.getMessage()),
                        null);
                return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
            }
            String fieldName = requiredField.getName();
            String errorMessage = "Please set <"
                    .concat(fieldName)
                    .concat(">: ")
                    .concat(fieldName.concat(" is required field"));
            BaseExceptionResponse<Object> baseResponse = new BaseExceptionResponse<>(false, errorMessage, null);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e) {
            BaseExceptionResponse<Object> baseResponse = new BaseExceptionResponse<>(
                    false,
                    "Please set all corresponding field !!!",
                    null);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
    }

    /**
     * this method is used in those places where any action has not been finished successfully
     *
     * @param error being passed to the method
     * @return the BaseExceptionResponse of the object
     */
    @ExceptionHandler(NotReadException.class)
    public ResponseEntity<BaseExceptionResponse<Object>> handleNotReadException(NotReadException error) {
        String errorMessage;
        BaseExceptionResponse<Object> baseResponse;
        errorMessage = error.getMessage();
        baseResponse = new BaseExceptionResponse<>(
                false,
                errorMessage,
                null);

        return new ResponseEntity<>(baseResponse, error.getHttpStatus());
    }
}
