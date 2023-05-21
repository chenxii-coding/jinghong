package com.chenxii.jinghong.gateway.api;

import com.chenxii.jinghong.common.entity.Response;
import com.chenxii.jinghong.common.entity.User;
import com.chenxii.jinghong.gateway.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jinghong/api")
public class GatewayApi {

    @Autowired
    private AuthService authService;

    @GetMapping("/gateway/userLogin")
    public Response<User> userLogin(@RequestParam String uid,
                                    @RequestParam String password) {
        return authService.login(uid, password);
    }

}
