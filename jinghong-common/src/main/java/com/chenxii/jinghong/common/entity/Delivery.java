package com.chenxii.jinghong.common.entity;


import java.io.Serializable;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName delivery
 */
@TableName(value = "delivery")
@Data
public class Delivery implements Serializable {

    private String id;

    private String deliveryNo;

    private String orderNo;

    private String destination;

    private String addressee;

    private String mobilePhone;

    private String status;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
