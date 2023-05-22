package com.chenxii.jinghong.goods.api;

import com.chenxii.jinghong.common.entity.Cart;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.goods.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jinghong/api")
public class CartApi {

    @Autowired
    private CartService cartService;

    @GetMapping("/goods/cart/{uid}")
    public Response<List<Cart>> queryCart(@PathVariable String uid) {
        return cartService.queryCart(uid);
    }

    @PostMapping("/goods/cart/{uid}/{goodsNo}")
    public Response<Void> addGoods(@PathVariable String uid,
                                   @PathVariable String goodsNo) {
        return cartService.addGoods(uid, goodsNo);
    }

    @PutMapping("/goods/cart/{uid}/{goodsNo}/{count}")
    public Response<Void> updateGoods(@PathVariable String uid,
                                      @PathVariable String goodsNo,
                                      @PathVariable int count) {
        return cartService.updateGoods(uid, goodsNo, count);
    }

    @DeleteMapping("/goods/cart/{uid}/{goodsNo}")
    public Response<Void> removeGoods(@PathVariable String uid,
                                      @PathVariable String goodsNo) {
        return cartService.removeGoods(uid, goodsNo);
    }
}
