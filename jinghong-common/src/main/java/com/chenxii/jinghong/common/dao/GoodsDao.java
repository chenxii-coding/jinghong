package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【goods】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.Goods
 */
@Mapper
public interface GoodsDao {

    int insert(Goods goods);

    List<Goods> queryAll();

    List<Goods> queryOnSaleGoods();

    List<Goods> queryByGoodsNoList(@Param("goodsNoList") List<String> goodsNoList);

    Goods queryByGoodsNo(String goodsNo);

}
