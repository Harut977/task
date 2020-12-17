package com.task.api.notification.response;

import com.task.api.base.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents the response for the notifications, which is extending from
 * the BaseResponse class. The class has an only field which is showing the success
 * of the deliver of the notification.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificationResponse extends BaseResponse {

    private boolean delivered;
}
