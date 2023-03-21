package com.riptide.ddplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Classes;
import org.apache.ibatis.annotations.Param;


public interface ClassesService extends IService<Classes> {
    APIResult getClassList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
