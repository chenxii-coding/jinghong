package com.chenxii.jinghong.user.api;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.common.utils.ResponseUtil;
import com.chenxii.jinghong.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jinghong/api")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{uid}")
    public Response<User> queryUser(@PathVariable String uid) {
        return ResponseUtil.success(userService.queryUser(uid));
    }
}
