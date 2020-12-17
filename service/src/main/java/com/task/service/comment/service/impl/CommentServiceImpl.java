package com.task.service.comment.service.impl;

import com.task.service.exception.models.NotReadException;
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

/**
 * This is the ServiceImplementation of the comments, that is implemented from the CommentService.
 * Here are the logics of the actions that can be done with the comments.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * used for mapping between the objects of the comment
     */
    private final CommentMapper commentMapper;

    /**
     * used for having connection with the database, for adding a comment there, or getting from there
     */
    private final CommentRepository commentRepository;

    /**
     * used for the business logic of the notification
     */
    private final NotificationService notificationService;

    /**
     * the logic of creating a comment, by passing the request of the comment to it and getting the success
     * the method is overridden from the CommentService interface
     *
     * @param commentRequest the request of the comment that is being created
     * @return a boolean result of the creation of the comment
     */
    @Override
    public boolean createComment(final CommentRequest commentRequest) {

        //saving the entity of the comment to the database and getting the entity
        CommentEntity savedCommentEntity = commentRepository
                .save(commentMapper.fromCommentRequestToCommentEntity(
                        commentRequest));

        //throwing exception in a random way
        try {
            BusinessLogic.doSomeWorkOnCommentCreation();
            new Thread(() -> notificationService.create(savedCommentEntity)).start();
        } catch (RuntimeException e) {
            commentRepository.delete(savedCommentEntity);
            return false;
        }
        return true;
    }

    /**
     * the logic of getting all the comments and returning its' List, this is overridden from the CommentService
     * interface
     * the method is overridden from the CommentService interface
     *
     * @param pageSize   is the number of comments on each page
     * @param pageNumber is the number of pages with comments
     * @return the List of CommentResponses
     */
    @Override
    public List<CommentResponse> getComments(final int pageSize, final int pageNumber) {

        //checking the number of comments on each page, if it is less than 1, or the number of pages
        //is less than 0, than an exception is thrown
        if (pageSize < 1 || pageNumber < 0) {
            throw new NotReadException("PageSize Or PageNumber not correct", HttpStatus.BAD_REQUEST);
        }

        //getting all the entities of the comments as a List from the database
        List<CommentEntity> commentEntities =
                commentRepository.
                        findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        //returning the List of comments as a Response, by mapping it using the commentMapper
        return commentMapper.fromCommentEntityListToCommentResponseList(commentEntities);
    }
}
