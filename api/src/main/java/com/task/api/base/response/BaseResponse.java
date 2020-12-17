package com.task.api.base.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * BaseResponse class represents the main class for all other responses,
 * all other responses are extended from this class.
 *
 * @author Harut
 * @since 12.17.2020
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private Long id;

    private Date createdDate;

    private Date lastModifiedDate;
}
