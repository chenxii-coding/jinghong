package com.chenxii.jinghong.goods.service;

import com.chenxii.jinghong.common.entity.Cart;
import com.chenxii.jinghong.common.entity.Response;

import java.util.List;

public interface CartService {

    Response<Void> addGoods(String uid, String goodsNo);

    Response<Void> removeGoods(String uid, String goodsNo);

    Response<Void> updateGoods(String uid, String goodsNo, int count);

    Response<List<Cart>> queryCart(String uid);

}
