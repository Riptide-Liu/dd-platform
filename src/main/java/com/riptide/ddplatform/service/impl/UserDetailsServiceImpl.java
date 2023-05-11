package com.riptide.ddplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.riptide.ddplatform.domin.LoginUser;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.exception.GlobalException;
import com.riptide.ddplatform.mapper.MenuMapper;
import com.riptide.ddplatform.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(wrapper);


        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new GlobalException(ApiEnum.PRODUCT_NOT_EXIST);
        }
        // 根据用户查询权限信息 添加到LoginUser中
        //List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));

        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
        //封装成UserDetails对象返回 
        return new LoginUser(user, permissionKeyList);
    }
}