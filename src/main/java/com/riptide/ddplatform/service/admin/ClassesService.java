package com.riptide.ddplatform.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Classes;
import org.apache.ibatis.annotations.Param;


public interface ClassesService extends IService<Classes> {
    APIResult getClassList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("queryValue") String queryValue);
}
