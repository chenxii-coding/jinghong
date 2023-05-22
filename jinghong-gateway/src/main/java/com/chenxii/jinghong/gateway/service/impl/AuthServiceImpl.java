package com.chenxii.jinghong.gateway.service.impl;

import com.chenxii.jinghong.common.dao.UserDao;
import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.common.utils.LogUtil;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.gateway.service.AuthService;
import com.chenxii.jinghong.gateway.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Override
    public Response<User> login(String uid, String password) {
        LogUtil.info("【账户】用户 {} 使用密码 {} 登陆", uid, password);
        User user = userDao.queryByUid(uid);
        if (user == null) {
            return ResponseUtil.failed("用户不存在");
        }

        if (!StringUtils.equals(password, user.getPassword())) {
            return ResponseUtil.failed("密码错误");
        }

        // 更新上次登陆时间
        userDao.updateLastLoginTime(uid);

        user.setToken(TokenUtil.createToken(uid));

        return ResponseUtil.success(user);
    }

}
