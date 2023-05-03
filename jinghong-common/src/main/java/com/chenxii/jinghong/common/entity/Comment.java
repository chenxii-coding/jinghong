package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName comment
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "comment")
@Data
public class Comment extends BaseEntity implements Serializable {

    private String id;

    private String orderNo;

    private String goodsNo;

    private String comment;

    private BigDecimal rate;

    private String image;

    private static final long serialVersionUID = 1L;

}
