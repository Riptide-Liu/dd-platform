package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}