package com.riptide.ddplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Role;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.domin.pojo.UserRole;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.domin.vo.UserVo;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.exception.GlobalException;
import com.riptide.ddplatform.mapper.RoleMapper;
import com.riptide.ddplatform.mapper.UserMapper;
import com.riptide.ddplatform.mapper.UserRoleMapper;
import com.riptide.ddplatform.service.UserService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public APIResult add(User user) {
        // 检查是否已存在用户
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUsername, user.getUsername());
        User selectUser = userMapper.selectOne(userWrapper);
        if (!Objects.isNull(selectUser)) {
            throw new GlobalException(ApiEnum.USERNAME_REGISTED);
        }

        // 密码加密后再存入数据库
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int result = userMapper.insert(user);
        if (result == 1) {
            LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
            roleWrapper.eq(Role::getRoleKey, user.getUserType());
            Role role = roleMapper.selectOne(roleWrapper);
            UserRole userRole = new UserRole(user.getId(), role.getId());
            return userRoleMapper.insert(userRole) == 1 ? ResultGenerator.genSuccess("新增用户成功！") : ResultGenerator.genFailed("新增用户失败：添加权限失败！");
        } else
            return ResultGenerator.genFailed("新增用户失败！");
    }

    @Override
    public APIResult delete(Long id) {
        try {
            userMapper.deleteById(id);
            LambdaQueryWrapper<UserRole> roleWrapper = new LambdaQueryWrapper<>();
            roleWrapper.eq(UserRole::getUser_id, id);
            userRoleMapper.delete(roleWrapper);
            return ResultGenerator.genSuccess("删除用户成功");
        } catch (Exception e) {
            return ResultGenerator.genFailed("删除用户失败");
        }

    }

    @Override
    public APIResult edit(User user) {
        // 检查是否跟其它用户名重名
        User selectUser = userMapper.selectById(user.getId());
        user.setUsername(selectUser.getUsername());
        if (userMapper.updateById(user) == 1) {
            LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
            roleWrapper.eq(Role::getRoleKey, user.getUserType());
            Role new_role = roleMapper.selectOne(roleWrapper);

            roleWrapper.eq(Role::getRoleKey, selectUser.getUserType());
            Role old_role = roleMapper.selectOne(roleWrapper);

            userRoleMapper.updateUserRole(user.getId(), new_role.getId(), old_role.getId());
            return ResultGenerator.genSuccess("编辑用户成功！");
        } else
            return ResultGenerator.genFailed("编辑用户失败！");
    }

    @Override
    public APIResult selectItem(Long id) {
        User user = userMapper.selectById(id);
        if(Objects.isNull(user))
            return ResultGenerator.genFailed("查询用户失败");
        return ResultGenerator.genSuccess("查询用户成功", user);
    }

    @Override
    public APIResult getUserList(Integer pageNum, Integer pageSize, String queryValue) {
        //查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态是未删除的
        lambdaQueryWrapper.eq(User::getDelFlag, 0);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(User::getCreateTime);
        // 模糊查询
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryValue) , User::getUsername,  queryValue);
        //分页查询
        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        //封装查询结果
        List<UserVo> userVos = BeanCopyUtils.copyBeanList(page.getRecords(), UserVo.class);

        PageVo pageVo = new PageVo(userVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取用户列表成功",pageVo);
    }
}
