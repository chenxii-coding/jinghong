package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.GoodsDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【goods_detail】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.GoodsDetail
 */
@Mapper
public interface GoodsDetailDao {

    int insertBatch(@Param("goodsDetailList") List<GoodsDetail> goodsDetailList);

    List<GoodsDetail> queryByGoodsNo(String goodsNo);

}
