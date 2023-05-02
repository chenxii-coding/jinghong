package com.chenxii.jinghong.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName inventory
 */
@TableName(value = "inventory")
@Data
public class Inventory extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private Object count;

    private static final long serialVersionUID = 1L;

}
