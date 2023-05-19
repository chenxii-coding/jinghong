package com.chenxii.jinghong.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cart extends BaseEntity {

    private String id;

    private String uid;

    private String goodsNo;

    private Integer count;

    private String image;

    private String goodsName;

    private String brand;

    private BigDecimal price;

    private String categoryNo;

    private String categoryName;

    private Boolean isOnSale;

}
