package com.chenxii.jinghong.user.service.impl;

import com.chenxii.jinghong.common.dao.UserDao;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.common.utils.LogUtil;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Response<User> queryUser(String uid) {
        User user = userDao.queryByUid(uid);
        LogUtil.info("【账户】查询用户 {}", user);
        LogUtil.info("【商品评分】查询用户 {}", user);
        return ResponseUtil.success(user);
    }

}
