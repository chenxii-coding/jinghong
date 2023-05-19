package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【category】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.Cart
 */
@Mapper
public interface CartDao {

    int insert(Cart cart);

    int deleteByUidAndGoodsNo(@Param("uid") String uid,
                              @Param("goodsNo") String goodsNo);

    List<Cart> queryByUid(String uid);

    int updateCountByUidAndGoodsNo(@Param("uid") String uid,
                                   @Param("goodsNo") String goodsNo,
                                   @Param("count") int count);

}
