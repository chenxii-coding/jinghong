package com.chenxii.jinghong.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName t_test
 */
@TableName(value = "t_test")
@Data
public class Test implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String comment;

    /**
     *
     */
    private String createdBy;

    /**
     *
     */
    private Date createdTime;

    /**
     *
     */
    private String updatedBy;

    /**
     *
     */
    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
