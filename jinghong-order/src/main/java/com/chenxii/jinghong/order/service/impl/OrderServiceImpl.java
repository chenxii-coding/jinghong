package com.chenxii.jinghong.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chenxii.jinghong.common.constant.OrderStatus;
import com.chenxii.jinghong.common.dao.AutoNoDao;
import com.chenxii.jinghong.common.dao.OrderDao;
import com.chenxii.jinghong.common.dao.OrderDetailDao;
import com.chenxii.jinghong.common.entity.AutoNo;
import com.chenxii.jinghong.common.entity.Order;
import com.chenxii.jinghong.common.entity.OrderDetail;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.order.service.MQService;
import com.chenxii.jinghong.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AutoNoDao autoNoDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private MQService mqService;

    @Override
    public Response<Void> createOrder(Order order) {

        // 生成订单
        String orderNo = this.getOrderNo();
        order.setOrderNo(orderNo);

        BigDecimal amount = new BigDecimal("0");
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            amount = amount.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getCount())));
            orderDetail.setOrderNo(orderNo);
        }
        order.setAmount(amount);
        order.setStatus(OrderStatus.ORDER_STATUS_CREATING);

        orderDao.insert(order);

        orderDetailDao.insertBatch(orderDetailList);

        // 通过消息队列通知扣减库存
        Map<String, String> param = new HashMap<>();
        param.put("orderNo", orderNo);
        param.put("type", "create");
        mqService.sendMessage(JSONObject.toJSONString(param));

        return ResponseUtil.success();
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    private String getOrderNo() {
        AutoNo autoNo = new AutoNo();
        autoNo.setType("order");
        int no = autoNoDao.updateNo(autoNo);
        return "ORDER_" + StringUtils.leftPad("" + no, 6, '0');
    }
}
