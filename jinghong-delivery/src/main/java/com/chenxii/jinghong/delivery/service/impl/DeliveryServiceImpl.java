package com.chenxii.jinghong.delivery.service.impl;

import com.chenxii.jinghong.common.dao.DeliveryDao;
import com.chenxii.jinghong.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;

}
