package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Inventory;
import com.chenxii.jinghong.common.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【inventory】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.Inventory
 */
@Mapper
public interface InventoryDao {

    List<Inventory> queryByGoodsNoList(@Param("goodsNoList") List<String> goodsNoList);

    int updateWhenCreate(@Param("orderDetailList") List<OrderDetail> orderDetailList);

    int updateWhenCancel(@Param("orderDetailList") List<OrderDetail> orderDetailList);

    int insert(Inventory inventory);

}
