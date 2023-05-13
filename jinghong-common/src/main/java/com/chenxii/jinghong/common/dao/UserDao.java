package com.chenxii.jinghong.common.dao;

import com.chenxii.jinghong.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenxii
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-04-23 23:11:57
 * @Entity com.chenxii.jinghong.common.entity.User
 */
@Mapper
public interface UserDao {

    User queryByUid(String uid);

}




