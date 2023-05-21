package com.chenxii.jinghong.user.service.impl;

import com.chenxii.jinghong.common.dao.UserDao;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Response<User> queryUser(String uid) {
        User user = userDao.queryByUid(uid);
        log.info("【账户】查询用户 {}", user);
        return ResponseUtil.success(user);
    }

}
