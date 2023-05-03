package com.chenxii.jinghong.goods.service;

import com.chenxii.jinghong.common.entity.Goods;
import com.chenxii.jinghong.common.entity.Response;

import java.util.List;

public interface GoodsService {

    Response<Void> addGoods(Goods goods);

    Response<Void> updateGoods(Goods goods);

    Response<Void> delGoods(String goodsNo);

    Response<List<Goods>> queryGoods();

}
