package com.chenxii.jinghong.user.service.impl;

import com.chenxii.jinghong.common.dao.UserDao;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User queryUser(String uid) {
        User user = userDao.queryByUid(uid);
        log.info("find user: {}", user);
        return user;
    }
}
