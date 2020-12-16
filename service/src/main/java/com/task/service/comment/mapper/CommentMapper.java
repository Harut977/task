package com.task.service.comment.mapper;

import com.task.api.comment.request.CommentRequest;
import com.task.api.comment.response.CommentResponse;
import com.task.service.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final ModelMapper modelMapper;


    public CommentEntity fromCommentRequestToCommentEntity(CommentRequest commentRequest) {
        return CommentEntity.builder()
                .comment(commentRequest.getComment())
                .build();
    }

    public List<CommentResponse> fromCommentEntityListToCommentResponseList(List<CommentEntity> commentEntities) {
        return modelMapper.map(commentEntities, new TypeToken<List<CommentResponse>>() {
        }.getType());

    }
}
