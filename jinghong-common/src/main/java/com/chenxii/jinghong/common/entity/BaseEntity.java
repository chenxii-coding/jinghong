package com.chenxii.jinghong.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    protected String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createdTime;

    protected String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updatedTime;

}
