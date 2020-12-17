package com.task.api.notification;

import com.task.api.base.response.Response;
import com.task.api.notification.response.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * The NotificationApi interface represents the actions that can be taken with the notification.
 * The notifications can be gotten.
 *
 * @author Harut
 * @since 12.17.2020
 */
@RequestMapping("/notification")
public interface NotificationApi {

    /**
     * The method is for getting all the notifications with a list, taking into account the number of
     * pages of the notifications and the number of notifications on each page.
     *
     * @param pageSize   is the number of notifications on a single page
     * @param pageNumber is the number of the pages of notifications
     * @return the list of NotificationResponse
     */
    @GetMapping
    ResponseEntity<Response<List<NotificationResponse>>> getNotifications(
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
    );
}
