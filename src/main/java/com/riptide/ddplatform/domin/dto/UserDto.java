package com.riptide.ddplatform.domin.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "Id不能为空", groups = {ValidatorGroups.Update.class})
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {ValidatorGroups.Add.class})
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private int status;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private int sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1教师，2普通用户）
     */
    @NotNull(message = "用户类型不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private int userType;
}
