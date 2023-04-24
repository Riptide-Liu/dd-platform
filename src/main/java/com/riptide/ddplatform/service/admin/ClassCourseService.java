package com.riptide.ddplatform.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.domin.pojo.Classes;
import org.apache.ibatis.annotations.Param;


public interface ClassCourseService extends IService<ClassCourse> {
    APIResult getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("classId") Long classId, @Param("queryValue") String queryValue);
}
