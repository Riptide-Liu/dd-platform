package com.riptide.ddplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import org.apache.ibatis.annotations.Param;


public interface UserCourseService extends IService<ClassCourse> {
    APIResult getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("queryValue") String queryValue);
}
