package com.chenxii.jinghong.goods.service.impl;

import com.chenxii.jinghong.common.constant.GoodsRate;
import com.chenxii.jinghong.common.dao.CartDao;
import com.chenxii.jinghong.common.entity.Cart;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.RequestUtil;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.CartService;
import com.chenxii.jinghong.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private GoodsService goodsService;

    @Override
    public Response<Void> addGoods(String uid, String goodsNo) {
        log.info("【购物车】用户 {} 添加商品 {} 到购物车", uid, goodsNo);
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setGoodsNo(goodsNo);
        cart.setCount(1);
        cartDao.insert(cart);

        // 更新评分
        goodsService.updateRate(uid, goodsNo, GoodsRate.GOODS_RATE_CART);

        return ResponseUtil.success();
    }

    @Override
    public Response<Void> updateGoods(String uid, String goodsNo, int count) {
        log.info("【购物车】用户 {} 修改商品 {} 的数量 {}", uid, goodsNo, count);
        if (count <= 0) {
            return ResponseUtil.failed("至少要留一件商品在购物车");
        }
        cartDao.updateCountByUidAndGoodsNo(uid, goodsNo, count);
        return ResponseUtil.success();
    }

    @Override
    public Response<Void> removeGoods(String uid, String goodsNo) {
        log.info("【购物车】用户 {} 将商品 {} 移除购物车", uid, goodsNo);
        cartDao.deleteByUidAndGoodsNo(uid, goodsNo);

        // 更新评分
        this.goodsService.updateRate(uid, goodsNo, GoodsRate.GOODS_RATE_REMOVE_CART);

        return ResponseUtil.success();
    }

    @Override
    public Response<List<Cart>> queryCart(String uid) {

        String currentUid = RequestUtil.currentUid();
        log.info("【测试】尝试获取当前登陆用户 UID: {}", currentUid);

        log.info("【购物车】用户 {} 查询购物车", uid);
        List<Cart> cartList = cartDao.queryByUid(uid);
        if (CollectionUtils.isEmpty(cartList)) {
            return ResponseUtil.failed("购物车里还没有商品");
        } else {
            return ResponseUtil.success(cartList);
        }
    }
}
