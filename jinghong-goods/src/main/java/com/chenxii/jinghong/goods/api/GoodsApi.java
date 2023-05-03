package com.chenxii.jinghong.goods.api;

import com.chenxii.jinghong.common.entity.Goods;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jinghong/api")
public class GoodsApi {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public Response<List<Goods>> queryGoods() {
        return goodsService.queryGoods();
    }

    @PutMapping("/goods")
    public Response<Void> addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }
}
