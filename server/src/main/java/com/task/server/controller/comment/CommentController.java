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

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

    private final RequestFieldsValidator requestFieldsValidator;

    private final CommentService commentService;

    @Override
    public ResponseEntity<Response<Boolean>> createComment(
            CommentRequest commentRequest, Errors errors) {

        requestFieldsValidator.validate(commentRequest);

        requestFieldsValidator.validate(errors);

        boolean created = commentService.createComment(commentRequest);

        String message = created ?
                "Comment Created" :
                "Comment Not Created";

        Response<Boolean> response =
                new Response<>(created, message, created);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<List<CommentResponse>>> getComment(int pageSize, int pageNumber) {
        List<CommentResponse> commentResponses = commentService.getComments(pageSize, pageNumber);
        Response<List<CommentResponse>> response =
                new Response<>(true, "ok", commentResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
