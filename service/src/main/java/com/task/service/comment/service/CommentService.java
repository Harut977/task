package com.task.service.comment.service;

import com.task.api.base.response.SuccessResponse;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;

import java.util.List;

/**
 * This is the Service interface of the comments, used for the business logic of the actions connected
 * with the comments.
 *
 * @author Harut
 * @since 12.17.2020
 */
public interface CommentService {

    /**
     * the comment can be created, using the request of the comment and returning its' success
     *
     * @param commentRequest the request of the comment that is being created
     * @return the boolean result of the creation
     */
    SuccessResponse createComment(CommentRequest commentRequest);

    /**
     * the comments can be gotten, using the pagination of them
     *
     * @param pageSize   is the number of comments on each page
     * @param pageNumber is the number of pages with comments
     * @return the List of CommentResponse
     */
    List<CommentResponse> getComments(int pageSize, int pageNumber);

}
