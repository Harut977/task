package com.task.api.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This represents the request for the comments, which includes a String of the comment.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private String comment;
}
