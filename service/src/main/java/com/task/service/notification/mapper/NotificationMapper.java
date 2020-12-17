package com.task.service.notification.mapper;

import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;
import com.task.service.notification.entity.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is for mapping between notification models
 *
 * @author Harut
 * @since 12.17.2020
 */
@Component
@RequiredArgsConstructor
public class NotificationMapper {

    /**
     * used for mapping from one object of a notification to another one
     */
    private final ModelMapper modelMapper;

    /**
     * this method creates NotificationEntity and connects with CommentEntity's id with a OneToOne connection
     *
     * @param commentEntity the entity of the comment from the database
     * @return the NotificationEntity object
     */
    public NotificationEntity buildNotificationEntity(CommentEntity commentEntity) {
        return NotificationEntity.builder()
                .commentEntity(commentEntity)
                .delivered(true)
                .build();
    }

    /**
     * mapping from NotificationEntity List to the List of NotificationResponse using the madelMapper
     *
     * @param notificationEntities the list of NotificationEntities
     * @return the List of NotificationResponses
     */
    public List<NotificationResponse> fromNotificationEntityListToNotificationResponseList(List<NotificationEntity> notificationEntities) {
        return modelMapper.map(notificationEntities, new TypeToken<List<NotificationResponse>>() {
        }.getType());

    }
}
