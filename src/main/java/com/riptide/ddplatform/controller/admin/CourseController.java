package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ClassesDto;
import com.riptide.ddplatform.domin.dto.CourseChapterDto;
import com.riptide.ddplatform.domin.dto.CourseDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.pojo.Course;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import com.riptide.ddplatform.service.ClassesService;
import com.riptide.ddplatform.service.CourseService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@Api("课程模块")
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add") // 增加课程
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody CourseDto courseDto){
        Course course = BeanCopyUtils.copyBean(courseDto, Course.class);
        return courseService.save(course)? ResultGenerator.genSuccess("增加课程成功"):ResultGenerator.genFailed("增加课程失败！");
    }

    @PostMapping("/edit") // 编辑课程
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody CourseDto courseDto){
        Course course = BeanCopyUtils.copyBean(courseDto, Course.class);
        return courseService.updateById(course)? ResultGenerator.genSuccess("编辑课程成功"):ResultGenerator.genFailed("编辑课程失败！");
    }

    @PostMapping("/delete") // 删除课程
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<Long> ids){
        return courseService.removeByIds(ids)? ResultGenerator.genSuccess("删除课程成功"):ResultGenerator.genFailed("删除课程失败！");
    }

    @GetMapping("/item") // 查询课程
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
        Course result = courseService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取课程成功",result):ResultGenerator.genFailed("获取课程失败！");

    }

    @GetMapping("/list") // 查询所有班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                             @NotNull @RequestParam(value = "page_size")Integer pageSize,
                             @RequestParam(value = "query_value")String queryValue){
        return courseService.getCourseList(pageNum, pageSize, queryValue);
    }
}
