package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role>  {
}
