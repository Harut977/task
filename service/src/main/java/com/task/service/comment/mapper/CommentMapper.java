package com.task.service.comment.mapper;

import com.task.api.base.response.SuccessResponse;
import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import com.task.service.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is used for mapping from one model of a comment to another one.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Component
@RequiredArgsConstructor
public class CommentMapper {

    /**
     * this is used for mapping between the object models of the comment
     */
    private final ModelMapper modelMapper;

    /**
     * method used for getting an Entity model of the comment from the request of the comment
     *
     * @param commentRequest is the request of the comment, which is an only String in it
     * @return the gotten Entity object of the comment
     */
    public CommentEntity fromCommentRequestToCommentEntity(CommentRequest commentRequest) {
        return CommentEntity.builder()
                .comment(commentRequest.getComment())
                .build();
    }

    /**
     * method used for getting List of CommentResponses from the List of CommentEntities
     *
     * @param commentEntities the list of the comments, gotten from the database
     * @return the List of the CommentResponse
     */
    public List<CommentResponse> fromCommentEntityListToCommentResponseList(List<CommentEntity> commentEntities) {
        return modelMapper.map(commentEntities, new TypeToken<List<CommentResponse>>() {
        }.getType());

    }

    public SuccessResponse fromCommentEntityToSuccessResponse(CommentEntity savedCommentEntity, boolean success) {
        return SuccessResponse.builder()
                .objectId(savedCommentEntity.getId())
                .success(success)
                .build();

    }
}
