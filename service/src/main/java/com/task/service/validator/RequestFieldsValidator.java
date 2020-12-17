package com.task.service.validator;

import com.task.service.exception.handler.ErrorFieldResponse;
import com.task.service.exception.handler.ValidationError;
import com.task.service.exception.models.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for validating a request
 *
 * @author Harut
 * @since 12.17.2020
 */
@Component
public class RequestFieldsValidator {

    /**
     * This method validates the fields of the request
     *
     * @param errors to validate its' fields
     */
    public void validate(final Errors errors) {
        List<ErrorFieldResponse> errorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                ErrorFieldResponse fieldError = new ErrorFieldResponse();
                fieldError.setField(error.getObjectName());
                fieldError.setMessage(error.getDefaultMessage());
                errorList.add(fieldError);
            });
            throw new BaseException(ValidationError.VALIDATION_ERROR, errorList);
        }
    }

    /**
     * checking whether the object is null or not
     *
     * @param obj passed to the method to check it
     */
    public void validate(final Object obj) {
        if (obj == null) {
            throw new BaseException("Please send correct data");
        }
    }
}
