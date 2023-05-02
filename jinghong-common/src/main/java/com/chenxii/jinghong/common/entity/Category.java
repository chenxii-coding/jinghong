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
public class Category extends BaseEntity implements Serializable {

    private String id;

    private String categoryNo;

    private String categoryName;

    private String parentCategory;

    private static final long serialVersionUID = 1L;

}
