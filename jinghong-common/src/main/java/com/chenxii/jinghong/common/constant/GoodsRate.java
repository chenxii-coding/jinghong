package com.chenxii.jinghong.common.constant;

/**
 * 用户对商品的评分表
 * 新的评分会覆盖原有的评分
 */
public class GoodsRate {

    /**
     * 点击进入商品详情 +1
     */
    public static final int GOODS_RATE_CLICK = 1;

    /**
     * 点击收藏 +3
     */
    public static final int GOODS_RATE_FAVORITE = 3;

    /**
     * 移除收藏 0
     */
    public static final int GOODS_RATE_REMOVE_FAVORITE = 0;

    /**
     * 点击分享 +2
     */
    public static final int GOODS_RATE_SHARE = 2;

    /**
     * 加入购物车 +4
     */
    public static final int GOODS_RATE_CART = 4;

    /**
     * 移除购物车 0
     */
    public static final int GOODS_RATE_REMOVE_CART = 0;

    /**
     * 购买 +5
     */
    public static final int GOODS_RATE_BUY = 5;

}
