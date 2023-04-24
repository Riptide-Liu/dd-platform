package com.riptide.ddplatform.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseService extends IService<Course> {
    APIResult getCourseList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("queryValue") String queryValue);
}
