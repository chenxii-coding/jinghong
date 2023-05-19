package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName inventory
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "inventory")
@Data
public class Inventory extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private int count;

    private static final long serialVersionUID = 1L;

}
