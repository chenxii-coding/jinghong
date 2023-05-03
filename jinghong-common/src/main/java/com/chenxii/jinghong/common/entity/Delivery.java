package com.chenxii.jinghong.common.entity;


import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName delivery
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "delivery")
@Data
public class Delivery extends BaseEntity implements Serializable {

    private String id;

    private String deliveryNo;

    private String orderNo;

    private String destination;

    private String addressee;

    private String mobilePhone;

    private String status;

    private static final long serialVersionUID = 1L;

}
