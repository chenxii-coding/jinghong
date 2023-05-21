package com.chenxii.jinghong.gateway.service;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;

public interface AuthService {

    Response<User> login(String uid, String password);

}
