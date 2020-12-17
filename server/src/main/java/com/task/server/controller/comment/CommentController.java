package com.task.server.controller.comment;

import com.task.api.base.response.Response;
import com.task.api.comment.CommentApi;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import com.task.service.comment.service.CommentService;
import com.task.service.validator.RequestFieldsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The CommentController is for getting the request, passing to the logic of the action, getting the response
 * and passing again to the front. It is implemented from the CommentApi interface.
 *
 * @author Harut
 * @since 12.17.2020
 */
@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

    /**
     * this is used for validating the requests and their fields
     */
    private final RequestFieldsValidator requestFieldsValidator;

    /**
     * is for using the business logic of the action
     */
    private final CommentService commentService;

    /**
     * implementation of creating a comment, overridden from the CommentApi
     * This method is used for creating a comment
     *
     * @param commentRequest is the comment that is being written
     * @param errors         shows the errors while creating a comment,it can be caused
     * @return the boolean response representing it is created successfully or not
     */
    @Override
    public ResponseEntity<Response<Boolean>> createComment(
            final CommentRequest commentRequest, final Errors errors) {

        //validates the request, for not being null
        requestFieldsValidator.validate(commentRequest);

        //validates the fields of the request
        requestFieldsValidator.validate(errors);

        //creates the comment, and gives result of its' success
        boolean created = commentService.createComment(commentRequest);

        //creates a message depending on the response
        String message = created ?
                "Comment Created" :
                "Comment Not Created";

        //creates the response depending on the results, which should be passed to front
        Response<Boolean> response =
                new Response<>(created, message, created);

        //returns the ResponseEntity, which includes the response, and the HttpStatus.OK on anytime, but the
        //response can differ
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * The implementation of the method getComment, which is overridden from the CommentApi
     * This method is used for getting all the comments
     *
     * @param pageSize   is the number of the comments on per page
     * @param pageNumber is the number of the pages that have comments in them
     * @return the List of CommentResponses
     */
    @Override
    public ResponseEntity<Response<List<CommentResponse>>> getComment(final int pageSize, final int pageNumber) {

        //returns the comments, depending on the pagination
        List<CommentResponse> commentResponses = commentService.getComments(pageSize, pageNumber);

        //creates a message depending on the response
        String message = commentResponses.size() > 0 ?
                "Here is your data" :
                "You don't have data with this infos";

        //if here is managed in the code, it means that everything has been ok, and it creates the response,
        //depending on the message and response of the comment
        Response<List<CommentResponse>> response =
                new Response<>(true, message, commentResponses);

        //returns the ResponseEntity, the Status is ok anyway, the response is the gotten, even if it's empty
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
