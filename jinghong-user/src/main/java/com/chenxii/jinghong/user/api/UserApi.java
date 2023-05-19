package com.chenxii.jinghong.user.api;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jinghong/api")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{uid}")
    public Response<User> queryUser(@PathVariable String uid) {
        return userService.queryUser(uid);
    }

    @GetMapping("/user/login")
    public Response<User> login(@RequestParam String uid,
                                @RequestParam String password) {
        return userService.login(uid, password);
    }

}
