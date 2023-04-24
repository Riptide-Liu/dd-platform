package com.riptide.ddplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ClassCourseMapper extends BaseMapper<ClassCourse> {
}
