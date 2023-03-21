package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
