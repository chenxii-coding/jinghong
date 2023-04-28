package com.chenxii.jinghong.common.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName order_detail
 */
@TableName(value = "order_detail")
@Data
public class OrderDetail implements Serializable {

    private String id;

    private String orderNo;

    private String goodsNo;

    private BigDecimal price;

    private Object count;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
