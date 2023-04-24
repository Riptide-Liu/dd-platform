package com.riptide.ddplatform.controller;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.domin.pojo.Course;
import com.riptide.ddplatform.service.UserCourseService;
import com.riptide.ddplatform.service.admin.CourseService;
import com.riptide.ddplatform.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
@Api("课程模块")
@RequestMapping("/course")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/item") // 查询课程
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
        Course result = courseService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取课程成功",result):ResultGenerator.genFailed("获取课程失败！");

    }


    @GetMapping("/list") // 查询所有课程
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                             @NotNull @RequestParam(value = "page_size")Integer pageSize,
                             @RequestParam(value = "query_value")String queryValue){

        return userCourseService.getList(pageNum, pageSize, queryValue);
    }

}
