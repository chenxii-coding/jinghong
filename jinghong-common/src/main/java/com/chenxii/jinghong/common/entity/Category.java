package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName category
 */
@TableName(value = "category")
@Data
public class Category implements Serializable {

    private String id;

    private String category1;

    private String category2;

    private String category3;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
