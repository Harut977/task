package com.task.service.notification.service;

import com.task.api.notification.response.NotificationResponse;
import com.task.service.comment.entity.CommentEntity;

import java.util.List;

public interface NotificationService {

    void create(final CommentEntity commentEntity);

    List<NotificationResponse> getNotifications(int pageSize, int pageNumber);
}
