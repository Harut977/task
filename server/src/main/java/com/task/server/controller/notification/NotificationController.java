package com.task.server.controller.notification;

import com.task.api.base.response.Response;
import com.task.api.comment.response.CommentResponse;
import com.task.api.notification.NotificationApi;
import com.task.api.notification.response.NotificationResponse;
import com.task.service.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The NotificationController is for getting the notification's request, passing the action to the business logic,
 * getting the response from there and again passing to front.
 * It is implemented from the NotificationApi interface.
 *
 * @author Harut
 * @since 12.17.2020
 */
@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {

    /**
     * this is for using the business logic of the actions
     */
    private final NotificationService notificationService;

    /**
     * this method is the implementation of getting the notifications, which is overridden from the
     * NotificationApi interface
     * this is used for getting all the notifications
     *
     * @param pageSize   is the number of notifications on a single page
     * @param pageNumber is the number of the pages of notifications
     * @return the List of NotificationResponse
     */
    @Override
    public ResponseEntity<Response<List<NotificationResponse>>> getNotifications(final int pageSize, final int pageNumber) {

        //creates the notificationsResponses List by getting it from the business logic, using the service
        List<NotificationResponse> notificationResponses = notificationService.getNotifications(pageSize, pageNumber);

        //creates a message depending on the response
        String message = notificationResponses.size() > 0 ?
                "Here is your data" :
                "You don't have data with this infos";

        //creates the response, which depends on the message and notificationResponses List
        Response<List<NotificationResponse>> response =
                new Response<>(true, message, notificationResponses);

        //returns the ResponseEntity, where the HttpStatus is OK anyway, and also includes the response which is gotten
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
