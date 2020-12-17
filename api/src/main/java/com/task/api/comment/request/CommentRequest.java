package com.task.api.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Comment han not be null")
    @NotBlank(message = "Comment han not be blank")
    private String comment;
}
