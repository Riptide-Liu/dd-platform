package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    int updateUserRole(@Param("user_id") Long user_id, @Param("new_role_id") Long new_role_id, @Param("old_role_id") Long old_role_id);
}
