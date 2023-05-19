package com.chenxii.jinghong.order.service;

import com.chenxii.jinghong.common.entity.Order;
import com.chenxii.jinghong.common.entity.Response;

public interface OrderService {

    Response<Void> createOrder(Order order);

}
