package com.riptide.ddplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.CourseResource;
import org.apache.ibatis.annotations.Param;

public interface CourseResourceService extends IService<CourseResource> {
    APIResult getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
