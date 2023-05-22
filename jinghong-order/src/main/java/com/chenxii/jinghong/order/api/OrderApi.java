package com.chenxii.jinghong.order.api;

import com.chenxii.jinghong.common.entity.Order;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.order.service.MQService;
import com.chenxii.jinghong.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jinghong/api")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MQService mqService;

    @PostMapping("/order/create")
    public Response<Void> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

}
