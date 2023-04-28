package com.chenxii.jinghong.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName order
 */
@TableName(value = "order")
@Data
public class Order implements Serializable {

    private String id;

    private String orderNo;

    private BigDecimal amount;

    private String paidBy;

    private String status;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}
