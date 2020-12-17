package com.task.api.base.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used for giving a response after the creation
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {

    private Long objectId;

    private boolean success;
}
