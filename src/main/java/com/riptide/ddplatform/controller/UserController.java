package com.riptide.ddplatform.controller;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.domin.dto.UserDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.service.UserService;
import com.riptide.ddplatform.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@Api("用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/user/add") // 增加用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult addUser(@Validated(value = ValidatorGroups.Add.class) @RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userService.add(user);
    }

    @PostMapping("/admin/user/edit") // 编辑用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult editUser(@Validated(value = ValidatorGroups.Update.class) @RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userService.edit(user);
    }

    @PostMapping("/admin/user/delete") // 删除用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult deleteUser(@Validated(value = ValidatorGroups.Delete.class) @RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        return userService.delete(user.getId());
    }

    @GetMapping("/admin/user/item") // 查询用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getUserItem(@NotNull @RequestParam(value = "id") Long id){
        return userService.selectItem(id);
    }

    @GetMapping("/admin/user/list") // 查询所有用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getUserList(@NotNull @RequestParam(value = "page_num")Integer pageNum,@NotNull @RequestParam(value = "pageSize")Integer pageSize){

        return userService.getUserList(pageNum, pageSize);
    }
}
