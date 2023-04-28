package com.chenxii.jinghong.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName inventory_log
 */
@TableName(value = "inventory_log")
@Data
public class InventoryLog implements Serializable {

    private String id;

    private String goodsNo;

    private Object oldCount;

    private Object variation;

    private String type;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private static final long serialVersionUID = 1L;

}
