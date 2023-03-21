package com.riptide.ddplatform.controller;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public APIResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public APIResult logout() {
        return loginService.logout();
    }
}
