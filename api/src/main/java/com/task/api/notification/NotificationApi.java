package com.task.api.notification;

import com.task.api.base.response.Response;
import com.task.api.notification.response.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/notification")
public interface NotificationApi {

    @GetMapping
    ResponseEntity<Response<List<NotificationResponse>>> getNotifications(
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
    );
}
