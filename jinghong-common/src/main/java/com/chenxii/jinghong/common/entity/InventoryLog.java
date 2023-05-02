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
public class InventoryLog extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private Object oldCount;

    private Object variation;

    private String type;

    private static final long serialVersionUID = 1L;

}
