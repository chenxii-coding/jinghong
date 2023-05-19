package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【order_detail】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.OrderDetail
 */
@Mapper
public interface OrderDetailDao {

    int insertBatch(@Param("orderDetailList") List<OrderDetail> orderDetailList);

    List<OrderDetail> queryByOrderNo(String orderNo);

}
