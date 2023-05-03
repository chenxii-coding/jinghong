package com.chenxii.jinghong.goods.service.impl;

import com.chenxii.jinghong.common.dao.AutoNoDao;
import com.chenxii.jinghong.common.dao.GoodsDao;
import com.chenxii.jinghong.common.dao.GoodsDetailDao;
import com.chenxii.jinghong.common.entity.AutoNo;
import com.chenxii.jinghong.common.entity.Goods;
import com.chenxii.jinghong.common.entity.GoodsDetail;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.GoodsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsDetailDao goodsDetailDao;

    @Autowired
    private AutoNoDao autoNoDao;

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
}
