package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ClassesMapper extends BaseMapper<Classes> {
}
