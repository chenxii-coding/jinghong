package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName comment
 */
@TableName(value = "comment")
@Data
public class Comment implements Serializable {

    private String id;

    private String orderNo;

    private String goodsNo;

    private String comment;

    private BigDecimal rate;

    private String image;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
