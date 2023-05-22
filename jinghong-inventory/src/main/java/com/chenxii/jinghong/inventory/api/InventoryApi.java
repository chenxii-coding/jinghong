package com.chenxii.jinghong.inventory.api;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jinghong/api")
public class InventoryApi {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/inventory/{orderNo}/{type}")
    public Response<Void> updateInventory(@PathVariable String orderNo,
                                          @PathVariable String type) {
        return inventoryService.updateInventory(orderNo, type);
    }

}
