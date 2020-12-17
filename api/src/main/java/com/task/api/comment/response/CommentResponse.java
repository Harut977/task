package com.task.api.comment.response;

import com.task.api.base.response.BaseResponse;
import lombok.*;

/**
 * This CommentResponse is the response class for the comments. It is extended from the BaseResponse class.
 * It includes a String of the comment, as the request for the comment.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentResponse extends BaseResponse {

    private String comment;

}
