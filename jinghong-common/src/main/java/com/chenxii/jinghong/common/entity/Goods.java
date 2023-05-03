package com.chenxii.jinghong.common.entity;


import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @TableName goods
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "goods")
@Data
public class Goods extends BaseEntity implements Serializable {

    private String id;

    private String goodsNo;

    private String goodsName;

    private String categoryNo;

    private String brand;

    private BigDecimal price;

    private String image;

    private String tags;

    private List<String> tagsList;

    private boolean isOnSale;

    private List<GoodsDetail> goodsDetailList;

    private static final long serialVersionUID = 1L;

}
