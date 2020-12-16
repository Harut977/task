package com.task.api.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private Long id;

    private Date createdDate;

    private Date lastModifiedDate;
}
