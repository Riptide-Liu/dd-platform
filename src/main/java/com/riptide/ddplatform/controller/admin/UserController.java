package com.riptide.ddplatform.controller.admin;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.LoginUser;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.domin.dto.UserDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.service.UserService;
import com.riptide.ddplatform.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/user")
@Api(tags = "用户模块",description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add") // 增加用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    @ApiOperation(value = "添加用户",notes = "添加一个用户")
    public APIResult addUser(@Validated(value = ValidatorGroups.Add.class) @RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userService.add(user);
    }

    @PostMapping("/edit") // 编辑用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    @ApiOperation(value = "编辑用户",notes = "编辑一个用户")
    public APIResult editUser(@Validated(value = ValidatorGroups.Update.class) @RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userService.edit(user);
    }

    @PostMapping("/delete") // 删除用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult deleteUser(@RequestBody List<Long> ids){
        SecurityContext context = SecurityContextHolder.getContext();
        LoginUser userInfo = (LoginUser) context.getAuthentication().getPrincipal();
        for (Long id : ids) {
           if(id.equals(userInfo.getUser().getId()))
               return ResultGenerator.genFailed(ApiEnum.DELETE_FAILED);
        }
        return userService.removeByIds(ids)? ResultGenerator.genSuccess(ApiEnum.DELETE_SUCCESS):ResultGenerator.genFailed(ApiEnum.DELETE_FAILED);
    }

    @GetMapping("/item") // 查询用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getUserItem(@NotNull @RequestParam(value = "id") Long id){
        return userService.selectItem(id);
    }

    @GetMapping("/list") // 查询所有用户
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getUserList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                                 @NotNull @RequestParam(value = "page_size")Integer pageSize,
                                 @RequestParam(value = "query_value")String queryValue){
        return userService.getUserList(pageNum, pageSize, queryValue);
    }
}
