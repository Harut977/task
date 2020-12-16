package com.task.api.comment;

import com.task.api.base.response.Response;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/comment")
public interface CommentApi {

    @PostMapping
    ResponseEntity<Response<Boolean>> createComment(
            @RequestBody CommentRequest commentRequest,
            Errors errors);

    @GetMapping
    ResponseEntity<Response<List<CommentResponse>>> getComment(
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
    );
}
