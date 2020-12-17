package com.task.api.comment;

import com.task.api.base.response.Response;
import com.task.api.base.response.SuccessResponse;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This CommentApi interface represents the actions which can be taken with the comment.
 * The comments can be created and gotten.
 *
 * @author Harut
 * @since 12.17.2020
 */
@RequestMapping("/comment")
public interface CommentApi {

    /**
     * This method is the action that is called when a comment is being added,
     * it includes the request of the comment and errors if they're present.
     *
     * @param commentRequest is the comment that is being written
     * @param errors         shows the errors while creating a comment,it can be caused
     * @return a boolean expression whether the comment was successfully written or not
     */
    @PostMapping
    ResponseEntity<Response<SuccessResponse>> createComment(
            @Valid @RequestBody CommentRequest commentRequest,
            Errors errors);

    /**
     * This method is the action for getting all the comments, taking into account the
     * number of pages and the number of comments on per page.
     *
     * @param pageSize   is the number of the comments on per page
     * @param pageNumber is the number of the pages that have comments in them
     * @return List of the responses for the comment
     */
    @GetMapping
    ResponseEntity<Response<List<CommentResponse>>> getComment(
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
    );
}
