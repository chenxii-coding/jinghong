package com.chenxii.jinghong.goods.api;

import com.chenxii.jinghong.common.entity.Goods;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jinghong/api")
public class GoodsApi {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public Response<List<Goods>> queryGoods() {
        return goodsService.queryGoods();
    }

    @GetMapping("/goods/onSale")
    public Response<List<Map<String, Object>>> queryOnSaleGoods() {
        return goodsService.queryOnSaleGoods();
    }

    @PutMapping("/goods")
    public Response<Void> addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @GetMapping("/goods/{goodsNo}")
    public Response<Goods> queryGoodsDetail(@PathVariable String goodsNo) {
        return goodsService.queryGoodsDetail(goodsNo);
    }

    @PostMapping("/goods/share/{uid}/{goodsNo}")
    public Response<Void> share(@PathVariable String uid,
                                @PathVariable String goodsNo) {
        return goodsService.share(uid, goodsNo);
    }

    @PostMapping("/goods/favorite/{uid}/{goodsNo}")
    public Response<Void> favorite(@PathVariable String uid,
                                   @PathVariable String goodsNo) {
        return goodsService.favorite(uid, goodsNo);
    }

    @GetMapping("/goods/favorite/{uid}")
    public Response<List<Goods>> queryFavorite(@PathVariable String uid) {
        return goodsService.queryFavorite(uid);
    }

    @DeleteMapping("/goods/removeFavorite/{uid}/{goodsNo}")
    public Response<Void> removeFavorite(@PathVariable String uid,
                                         @PathVariable String goodsNo) {
        return goodsService.removeFavorite(uid, goodsNo);
    }
}
