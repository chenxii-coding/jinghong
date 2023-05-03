package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName order
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "order")
@Data
public class Order extends BaseEntity implements Serializable {

    private String id;

    private String orderNo;

    private BigDecimal amount;

    private String paidBy;

    private String status;

    private static final long serialVersionUID = 1L;
}
