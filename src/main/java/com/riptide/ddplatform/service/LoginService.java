package com.riptide.ddplatform.service;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.User;

public interface LoginService {
    APIResult login(User user);

    APIResult logout();
}
