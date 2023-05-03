package com.chenxii.jinghong.common.entity;

import java.io.Serializable;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName goods_detail
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "goods_detail")
@Data
public class GoodsDetail extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private String descriptionItem;

    private String description;

    private int index;

    private static final long serialVersionUID = 1L;

}
