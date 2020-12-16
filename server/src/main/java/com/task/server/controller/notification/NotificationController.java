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

@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<Response<List<NotificationResponse>>> getNotifications(int pageSize, int pageNumber) {
        List<NotificationResponse> commentResponses = notificationService.getNotifications(pageSize, pageNumber);
        Response<List<NotificationResponse>> response =
                new Response<>(true, "ok", commentResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
