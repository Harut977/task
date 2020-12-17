package com.task.service.notification.service;

import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;

import java.util.List;

/**
 * This is the Service interface of the notifications, used for the business logic of the actions with the
 * notifications
 *
 * @author Harut
 * @since 12.17.2020
 */
public interface NotificationService {

    /**
     * method to create a notification, which is void. Passing the entity of the comment to it and returning nothing
     *
     * @param commentEntity the entity of the comment given to the creation of notification
     */
    void create(CommentEntity commentEntity);

    /**
     * method to get all the notifications, by passing the pagination to it and getting the notifications as a List
     *
     * @param pageSize   the number of notifications on per page
     * @param pageNumber the number of pages with notifications
     * @return the List of NotificationResponse
     */
    List<NotificationResponse> getNotifications(int pageSize, int pageNumber);
}
