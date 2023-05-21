package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Rate;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenxii
 * @description 针对表【rate】的数据库操作Mapper
 * @createDate 2023-05-21 00:15:21
 * @Entity com.chenxii.jinghong.common.entity.Rate
 */
@Mapper
public interface RateDao {

    int insert(Rate rate);

}
