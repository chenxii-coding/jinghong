package com.chenxii.jinghong.goods.service.impl;

import com.chenxii.jinghong.common.constant.GoodsRate;
import com.chenxii.jinghong.common.dao.*;
import com.chenxii.jinghong.common.entity.*;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsDetailDao goodsDetailDao;

    @Autowired
    private AutoNoDao autoNoDao;

    @Autowired
    private RateDao rateDao;

    @Autowired
    private FavoriteDao favoriteDao;

    public Response<Void> addGoods(Goods goods) {
        AutoNo autoNo = new AutoNo("goods");
        autoNoDao.updateNo(autoNo);
        // goodsNo
        goods.setGoodsNo("GOODS_" + StringUtils.leftPad(autoNo.getNo() + "", 4, '0'));
        // tags
        List<String> tagsList = goods.getTagsList();
        if (CollectionUtils.isNotEmpty(tagsList)) {
            goods.setTags(String.join("#", tagsList));
        }
        goodsDao.insert(goods);

        // detail
        List<GoodsDetail> goodsDetailList = goods.getGoodsDetailList();
        if (CollectionUtils.isNotEmpty(goodsDetailList)) {
            int i = 0;
            for (GoodsDetail goodsDetail : goodsDetailList) {
                goodsDetail.setGoodsNo(goods.getGoodsNo());
                goodsDetail.setIndex(i++);
            }
            goodsDetailDao.insertBatch(goodsDetailList);
        }
        return ResponseUtil.success();
    }

    public Response<Void> updateGoods(Goods goods) {
        return null;
    }

    public Response<Void> delGoods(String goodsNo) {
        return null;
    }

    public Response<List<Goods>> queryGoods() {
        List<Goods> goodsList = goodsDao.queryAll();
        if (CollectionUtils.isNotEmpty(goodsList)) {
            for (Goods goodsItem : goodsList) {
                List<GoodsDetail> goodsDetailList = goodsDetailDao.queryByGoodsNo(goodsItem.getGoodsNo());
                goodsItem.setGoodsDetailList(goodsDetailList);
                String tags = goodsItem.getTags();
                if (StringUtils.isNotBlank(tags)) {
                    goodsItem.setTagsList(Arrays.asList(tags.split("#")));
                }
            }
        }
        return ResponseUtil.success(goodsList);
    }

    @Override
    public Response<List<Map<String, Object>>> queryOnSaleGoods() {
        List<Goods> goodsList = goodsDao.queryOnSaleGoods();
        if (CollectionUtils.isNotEmpty(goodsList)) {
            for (Goods goodsItem : goodsList) {
                String tags = goodsItem.getTags();
                if (StringUtils.isNotBlank(tags)) {
                    goodsItem.setTagsList(Arrays.asList(tags.split("#")));
                }
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>(3);

        Map<String, Object> typeNew = new HashMap<>();
        typeNew.put("typeName", "新品推荐");
        typeNew.put("goodsList", goodsList.subList(0, 10));
        resultList.add(typeNew);

        Map<String, Object> typeRecommend = new HashMap<>();
        typeRecommend.put("typeName", "猜你喜欢");
        typeRecommend.put("goodsList", goodsList.subList(10, 20));
        resultList.add(typeRecommend);

        Map<String, Object> typeAll = new HashMap<>();
        typeAll.put("typeName", "所有商品");
        typeAll.put("goodsList", goodsList);
        resultList.add(typeAll);
        return ResponseUtil.success(resultList);
    }

    @Override
    public Response<Goods> queryGoodsDetail(String goodsNo) {
        Goods goods = goodsDao.queryByGoodsNo(goodsNo);
        List<GoodsDetail> goodsDetailList = goodsDetailDao.queryByGoodsNo(goodsNo);

        String tags = goods.getTags();
        if (StringUtils.isNotBlank(tags)) {
            goods.setTagsList(Arrays.asList(tags.split("#")));
        }
        goods.setGoodsDetailList(goodsDetailList);

        return ResponseUtil.success(goods);
    }

    @Override
    public Response<Void> share(String uid, String goodsNo) {
        int goodsRate = GoodsRate.GOODS_RATE_SHARE;
        // 分享商品什么都不做，只更新评分
        log.info("【评分】用户 {} 分享商品 {} , 更新分数: {}", uid, goodsNo, goodsRate);
        this.updateRate(uid, goodsNo, goodsRate);
        return ResponseUtil.success();
    }

    @Override
    public Response<Void> favorite(String uid, String goodsNo) {
        log.info("【喜欢】用户 {} 喜欢了商品 {}", uid, goodsNo);
        Favorite favorite = new Favorite();
        favorite.setUid(uid);
        favorite.setGoodsNo(goodsNo);
        favoriteDao.insert(favorite);

        // 更新评分
        this.updateRate(uid, goodsNo, GoodsRate.GOODS_RATE_FAVORITE);

        return ResponseUtil.success();
    }

    @Override
    public Response<Void> removeFavorite(String uid, String goodsNo) {
        log.info("【移除收藏】用户 {} 将商品 {} 移除收藏", uid, goodsNo);
        favoriteDao.delete(uid, goodsNo);

        // 更新评分
        this.updateRate(uid, goodsNo, GoodsRate.GOODS_RATE_REMOVE_FAVORITE);
        return ResponseUtil.success();
    }

    @Override
    public void updateRate(String uid, String goodsNo, int rate) {
        log.info("【更新评分】更新用户 {} 对商品 {} 的评分为 {}", uid, goodsNo, rate);
        Rate goodsRate = new Rate();
        goodsRate.setUid(uid);
        goodsRate.setGoodsNo(goodsNo);
        goodsRate.setRate(rate);
        rateDao.insert(goodsRate);
        ResponseUtil.success();
    }
}
