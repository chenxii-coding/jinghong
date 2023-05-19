package com.chenxii.jinghong.order.api;

import com.chenxii.jinghong.common.entity.Order;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.order.service.MQService;
import com.chenxii.jinghong.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/jinghong/api")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MQService mqService;

    @PostMapping("/order")
    public Response<Void> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/order/orderMessage")
    public Response<Void> createMessage(@RequestBody Map<String, String> data) {
        mqService.sendMessage(data.get("message"));
        return ResponseUtil.success();
    }
}
