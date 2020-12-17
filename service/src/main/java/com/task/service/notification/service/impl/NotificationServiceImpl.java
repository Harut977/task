package com.task.service.notification.service.impl;

import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;
import com.task.service.exception.models.NotReadException;
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

/**
 * This is the ServiceImplementation of the notifications, that is implemented from the NotificationService
 * interface.
 * Here is written the logics of all the actions that can be done with the notifications.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    /**
     * used for having connection with the database, for saving notifications or getting from there
     */
    private final NotificationRepository notificationRepository;

    /**
     * used for mapping between notification's objects
     */
    private final NotificationMapper notificationMapper;

    /**
     * the logic of creating a notification, which returns nothing
     * the method is overridden from the NotificationService interface.
     *
     * @param commentEntity the entity of the comment given to the creation of notification
     */
    @Override
    public void create(final CommentEntity commentEntity) {

        //creates notification entity and gets the result
        NotificationEntity notificationEntity =
                notificationMapper
                        .buildNotificationEntity(commentEntity);

        //saves the notification entity in the database
        notificationEntity = notificationRepository.save(notificationEntity);

        //throwing exception in a random way
        try {
            BusinessLogic.doSomeWorkOnNotification();
        } catch (RuntimeException e) {
            notificationRepository.updateDeliveredFlag(notificationEntity.getId(), false);
        }
    }

    /**
     * the logic of getting all the notifications, getting them with a List
     * the method is overridden from the NotificationService interface
     *
     * @param pageSize   the number of notifications on per page
     * @param pageNumber the number of pages with notifications
     * @return the List of NotificationResponses
     */
    @Override
    public List<NotificationResponse> getNotifications(final int pageSize, final int pageNumber) {

        //checking the number of notifications on each page, if it is less than 1 or the number of pages is less than 0,
        //than an exception is thrown
        if (pageSize < 1 || pageNumber < 0) {
            throw new NotReadException("PageSize Or PageNumber not correct", HttpStatus.BAD_REQUEST);
        }

        //gets all the notifications from the database and keeps them as a list
        List<NotificationEntity> commentEntities =
                notificationRepository.
                        findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        //return the List of notification responses, mapped using the notificationMapper
        return notificationMapper.fromNotificationEntityListToNotificationResponseList(commentEntities);
    }
}
