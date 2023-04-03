package com.riptide.ddplatform.controller;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.LoginUser;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.service.LoginService;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/user/info")
    public APIResult getUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        LoginUser userInfo = (LoginUser) context.getAuthentication().getPrincipal();
        return ResultGenerator.genSuccess(userInfo);
    }
}
