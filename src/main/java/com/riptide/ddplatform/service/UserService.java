package com.riptide.ddplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService extends IService<User> {
    APIResult add(User user);
    APIResult delete(@Param("id") Long id);
    APIResult edit(User user);
    APIResult selectItem(@Param("id") Long id);
    APIResult getUserList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("queryValue") String queryValue);
}
