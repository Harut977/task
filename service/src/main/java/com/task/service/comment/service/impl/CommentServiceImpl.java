package com.task.service.comment.service.impl;

import com.project.service.exception.models.NotReadException;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import com.task.service.comment.entity.CommentEntity;
import com.task.service.comment.mapper.CommentMapper;
import com.task.service.comment.repository.CommentRepository;
import com.task.service.comment.service.CommentService;
import com.task.service.helper.BusinessLogic;
import com.task.service.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    private final NotificationService notificationService;

    @Override
    public boolean createComment(final CommentRequest commentRequest) {

        CommentEntity savedCommentEntity = commentRepository
                .save(commentMapper.fromCommentRequestToCommentEntity(
                        commentRequest));
        try {
            BusinessLogic.doSomeWorkOnCommentCreation();
            new Thread(() -> notificationService.create(savedCommentEntity)).start();
        } catch (RuntimeException e) {
            commentRepository.delete(savedCommentEntity);
            return false;
        }
        return true;
    }

    @Override
    public List<CommentResponse> getComments(final int pageSize,final int pageNumber) {
        if (pageSize < 1 || pageNumber < 0) {
            throw new NotReadException("PageSize Or PageNumber not correct", HttpStatus.BAD_REQUEST);
        }

        List<CommentEntity> commentEntities =
                commentRepository.
                        findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return commentMapper.fromCommentEntityListToCommentResponseList(commentEntities);
    }
}
