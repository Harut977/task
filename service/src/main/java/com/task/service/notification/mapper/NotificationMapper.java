package com.task.service.notification.mapper;

import com.task.api.comment.response.CommentResponse;
import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;
import com.task.service.notification.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationMapper {


    private final ModelMapper modelMapper;

    public NotificationEntity buildNotificationEntity(CommentEntity commentEntity) {
        return NotificationEntity.builder()
                .commentEntity(commentEntity)
                .delivered(true)
                .build();

    }

    public List<NotificationResponse> fromNotificationEntityListToNotificationResponseList(List<NotificationEntity> notificationEntities) {
        return modelMapper.map(notificationEntities, new TypeToken<List<NotificationResponse>>() {
        }.getType());

    }
}
