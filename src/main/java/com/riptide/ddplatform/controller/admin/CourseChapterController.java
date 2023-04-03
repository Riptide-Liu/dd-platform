package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.CourseChapterDto;
import com.riptide.ddplatform.domin.dto.CourseDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.Course;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import com.riptide.ddplatform.service.CourseChapterService;
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
@Api("课程章节模块")
@RequestMapping("/admin/course/chapter")
public class CourseChapterController {

    @Autowired
    private CourseChapterService courseChapterService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody CourseChapterDto courseChapterDto){
        CourseChapter courseChapter = BeanCopyUtils.copyBean(courseChapterDto, CourseChapter.class);
        return courseChapterService.save(courseChapter)? ResultGenerator.genSuccess("增加成功"):ResultGenerator.genFailed("增加失败！");
    }



    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody CourseChapterDto courseChapterDto){
        CourseChapter courseChapter = BeanCopyUtils.copyBean(courseChapterDto, CourseChapter.class);
        return courseChapterService.updateById(courseChapter)? ResultGenerator.genSuccess("编辑成功"):ResultGenerator.genFailed("编辑失败！");
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<Long> ids){
        return courseChapterService.removeByIds(ids)? ResultGenerator.genSuccess("删除成功"):ResultGenerator.genFailed("删除失败！");
    }

    @GetMapping("/item")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
        CourseChapter result = courseChapterService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取成功",result):ResultGenerator.genFailed("获取失败！");

    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                             @NotNull @RequestParam(value = "page_size")Integer pageSize,
                             @RequestParam(value = "query_value")String queryValue,
                             @RequestParam(value = "course_id")Long courseId){
        return courseChapterService.getList(pageNum, pageSize,courseId,queryValue);
    }
}
