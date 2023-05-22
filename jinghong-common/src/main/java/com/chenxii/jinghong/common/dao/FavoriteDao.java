package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Favorite;
import com.chenxii.jinghong.common.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenxii
 * @description 针对表【favorite】的数据库操作Mapper
 * @createDate 2023-05-21 00:24:40
 * @Entity generator.domain.Favorite
 */
@Mapper
public interface FavoriteDao {

    int insert(Favorite favorite);

    int delete(@Param("uid") String uid, @Param("goodsNo") String goodsNo);

    List<Goods> queryByUid(String uid);

}
