package com.chenxii.jinghong.common.entity;


import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName order_detail
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "order_detail")
@Data
public class OrderDetail extends BaseEntity implements Serializable {

    private String id;

    private String orderNo;

    private String goodsNo;

    private BigDecimal price;

    private Object count;

    private static final long serialVersionUID = 1L;

}
