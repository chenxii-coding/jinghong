package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenxii
 * @description 针对表【order】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.Order
 */
@Mapper
public interface OrderDao {

    int insert(Order order);

    Order queryByOrderNo(String orderNo);

    int updateOrderStatus(String orderNo, String orderStatus);

}
