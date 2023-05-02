package com.chenxii.jinghong.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BaseEntity {

    protected String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createdTime;

    protected String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updatedTime;

}
