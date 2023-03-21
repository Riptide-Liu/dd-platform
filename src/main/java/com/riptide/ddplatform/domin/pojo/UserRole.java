package com.riptide.ddplatform.domin.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_role")
public class UserRole implements Serializable {
    private Long user_id;
    private Long role_id;
}
