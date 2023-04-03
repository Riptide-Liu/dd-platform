package com.riptide.ddplatform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

public interface CourseChapterService extends IService<CourseChapter> {
    APIResult getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("courseId") Long courseId, @Param("queryValue") String queryValue);
}
