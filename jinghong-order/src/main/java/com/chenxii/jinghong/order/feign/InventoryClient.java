package com.chenxii.jinghong.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "JINGHONG-INVENTORY")
public interface InventoryClient {

    @PostMapping("/jinghong/api/inventory/{orderNo}/{type}")
    void updateInventory(@PathVariable(value = "orderNo") String orderNo,
                         @PathVariable(value = "type") String type);

}
