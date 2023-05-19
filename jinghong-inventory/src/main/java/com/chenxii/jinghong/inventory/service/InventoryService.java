package com.chenxii.jinghong.inventory.service;

import com.chenxii.jinghong.common.entity.Response;

public interface InventoryService {

    /**
     * 更新库存
     *
     * @param orderNo 订单号
     * @param type 下单 还是 取消订单
     */
    Response<Void> updateInventory(String orderNo, String type);

}
