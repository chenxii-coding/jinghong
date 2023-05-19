package com.chenxii.jinghong.inventory.api;

import com.chenxii.jinghong.inventory.service.InventoryService;
import com.chenxii.jinghong.inventory.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jinghong/api")
public class InventoryApi {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MQService mqService;

}
