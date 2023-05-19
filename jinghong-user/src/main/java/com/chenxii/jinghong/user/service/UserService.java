package com.chenxii.jinghong.user.service;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;

public interface UserService {

    Response<User> queryUser(String uid);

    Response<User> login(String uid, String password);

}
