package com.chenxii.jinghong.common.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;

/**
 * @TableName goods
 */
@TableName(value = "goods")
@Data
public class Goods extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private String goodsName;

    private String category;

    private String brand;

    private BigDecimal price;

    private String image;

    private String tags;

    private String isOnSale;

    private static final long serialVersionUID = 1L;

}
