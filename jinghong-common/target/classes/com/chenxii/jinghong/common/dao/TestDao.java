package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

    int insert(Test test);

}
