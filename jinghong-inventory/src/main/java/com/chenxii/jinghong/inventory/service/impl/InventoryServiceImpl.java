package com.chenxii.jinghong.inventory.service.impl;

import com.chenxii.jinghong.common.constant.OrderStatus;
import com.chenxii.jinghong.common.dao.InventoryDao;
import com.chenxii.jinghong.common.dao.InventoryLogDao;
import com.chenxii.jinghong.common.dao.OrderDao;
import com.chenxii.jinghong.common.dao.OrderDetailDao;
import com.chenxii.jinghong.common.entity.*;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private InventoryLogDao inventoryLogDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public Response<Void> updateInventory(String orderNo, String type) {
        if ("create".equals(type)) {
            return this.updateInventoryCreateOrder(orderNo);
        } else {
            return this.cancelOrder(orderNo);
        }
    }

    private Response<Void> updateInventoryCreateOrder(String orderNo) {

        // 查询订单
        Order order = orderDao.queryByOrderNo(orderNo);
        if (order == null) {
            log.error("【库存】扣减库存失败, 订单不存在, 订单号: {}", orderNo);
            return ResponseUtil.failed("下单失败，库存不足");
        }

        // 获取订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.queryByOrderNo(orderNo);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            log.warn("【库存】订单详情为空, 订单号: {}", orderNo);
            orderDao.updateOrderStatus(orderNo, OrderStatus.ORDER_STATUS_FAILED);
            return ResponseUtil.success();
        }

        List<String> goodsNoList = orderDetailList.stream().map(OrderDetail::getGoodsNo).collect(Collectors.toList());
        // 库存数据
        List<Inventory> inventoryList = inventoryDao.queryByGoodsNoList(goodsNoList);
        Map<String, Integer> goodsCountMap = inventoryList.stream()
                .collect(Collectors.toMap(Inventory::getGoodsNo, Inventory::getCount));

        for (OrderDetail orderDetail : orderDetailList) {
            if (goodsCountMap.get(orderDetail.getGoodsNo()) < orderDetail.getCount()) {
                log.error("【库存】扣减库存失败, 订单不存在, 订单号: {}, 商品号: {}", orderNo, orderDetail.getGoodsNo());
                orderDao.updateOrderStatus(orderNo, OrderStatus.ORDER_STATUS_FAILED);
                return ResponseUtil.failed("下单失败，库存不足");
            }
        }

        // 更新库存
        inventoryDao.updateWhenCreate(orderDetailList);

        // 更新订单状态
        orderDao.updateOrderStatus(orderNo, OrderStatus.ORDER_STATUS_TO_PAID);

        // 写库存记录
        InventoryLog inventoryLog;
        List<InventoryLog> inventoryLogList = new ArrayList<>(orderDetailList.size());
        for (OrderDetail orderDetail : orderDetailList) {
            inventoryLog = new InventoryLog();
            inventoryLog.setGoodsNo(orderDetail.getGoodsNo());
            inventoryLog.setOldCount(goodsCountMap.get(orderDetail.getGoodsNo()));
            inventoryLog.setVariation(orderDetail.getCount());
            inventoryLog.setType("扣减");
            inventoryLogList.add(inventoryLog);
        }
        inventoryLogDao.insert(inventoryLogList);

        return ResponseUtil.success();
    }

    private Response<Void> cancelOrder(String orderNo) {

        // 获取订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.queryByOrderNo(orderNo);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            log.warn("【库存】订单详情为空, 订单号: {}", orderNo);
            orderDao.updateOrderStatus(orderNo, OrderStatus.ORDER_STATUS_CANCEL);
            return ResponseUtil.success();
        }

        List<String> goodsNoList = orderDetailList.stream().map(OrderDetail::getGoodsNo).collect(Collectors.toList());
        // 库存数据
        List<Inventory> inventoryList = inventoryDao.queryByGoodsNoList(goodsNoList);
        Map<String, Integer> goodsCountMap = inventoryList.stream()
                .collect(Collectors.toMap(Inventory::getGoodsNo, Inventory::getCount));

        // 更新库存
        inventoryDao.updateWhenCancel(orderDetailList);

        // 更新订单状态
        orderDao.updateOrderStatus(orderNo, OrderStatus.ORDER_STATUS_CANCEL);

        // 写库存记录
        InventoryLog inventoryLog;
        List<InventoryLog> inventoryLogList = new ArrayList<>(orderDetailList.size());
        for (OrderDetail orderDetail : orderDetailList) {
            inventoryLog = new InventoryLog();
            inventoryLog.setGoodsNo(orderDetail.getGoodsNo());
            inventoryLog.setOldCount(goodsCountMap.get(orderDetail.getGoodsNo()));
            inventoryLog.setVariation(orderDetail.getCount());
            inventoryLog.setType("增加");
            inventoryLogList.add(inventoryLog);
        }
        inventoryLogDao.insert(inventoryLogList);

        return ResponseUtil.success();
    }
}
