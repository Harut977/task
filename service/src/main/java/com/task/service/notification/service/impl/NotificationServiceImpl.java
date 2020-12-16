package com.task.service.notification.service.impl;

import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;
import com.task.service.helper.BusinessLogic;
import com.task.service.notification.entity.NotificationEntity;
import com.task.service.notification.mapper.NotificationMapper;
import com.task.service.notification.repository.NotificationRepository;
import com.task.service.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    @Override
    public void create(final CommentEntity commentEntity) {

        NotificationEntity notificationEntity =
                notificationMapper
                        .buildNotificationEntity(commentEntity);

        notificationEntity = notificationRepository.save(notificationEntity);

        try {
            BusinessLogic.doSomeWorkOnNotification();
        } catch (RuntimeException e) {
            notificationRepository.updateDeliveredFlag(notificationEntity.getId(), false);
        }
    }

    @Override
    public List<NotificationResponse> getNotifications(int pageSize, int pageNumber) {
        if (pageSize < 1 || pageNumber < 0) {
            throw new com.project.service.exception.models.NotReadException("PageSize Or PageNumber not correct", HttpStatus.BAD_REQUEST);
        }

        List<NotificationEntity> commentEntities =
                notificationRepository.
                        findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return notificationMapper.fromNotificationEntityListToNotificationResponseList(commentEntities);
    }
}
