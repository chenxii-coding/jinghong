package com.chenxii.jinghong.goods.service;

import com.chenxii.jinghong.common.entity.Goods;
import com.chenxii.jinghong.common.entity.Response;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    Response<Void> addGoods(Goods goods);

    Response<Void> updateGoods(Goods goods);

    Response<Void> delGoods(String goodsNo);

    Response<List<Goods>> queryGoods();

    Response<List<Map<String, Object>>> queryOnSaleGoods();

    Response<Goods> queryGoodsDetail(String goodsNo);

    Response<Void> share(String uid, String goodsNo);

    Response<Void> favorite(String uid, String goodsNo);

    Response<List<Goods>> queryFavorite(String uid);

    Response<Void> removeFavorite(String uid, String goodsNo);

    void updateRate(String uid, String goodsNo, int rate);

}
