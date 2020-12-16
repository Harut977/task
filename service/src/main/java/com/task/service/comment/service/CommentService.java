package com.task.service.comment.service;

import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;

import java.util.List;

public interface CommentService {

    boolean createComment(CommentRequest commentRequest);

    List<CommentResponse> getComments(int pageSize, int pageNumber);

}
