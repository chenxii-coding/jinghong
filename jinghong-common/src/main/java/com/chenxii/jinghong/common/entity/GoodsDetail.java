package com.chenxii.jinghong.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName goods_detail
 */
@TableName(value = "goods_detail")
@Data
public class GoodsDetail extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private String descriptionItem;

    private String description;

    private static final long serialVersionUID = 1L;

}
