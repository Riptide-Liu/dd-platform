package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ClassCourseDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.service.admin.ClassCourseService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Api("班级课程计划模块")
@RequestMapping("/admin/class/course")
public class ClassCourseController {

    @Autowired
    private ClassCourseService classCourseService;

    @PostMapping("/add") // 增加班级课程计划
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody ClassCourseDto classCourseDto){
        ClassCourse classCourse = BeanCopyUtils.copyBean(classCourseDto, ClassCourse.class);
        return classCourseService.save(classCourse)? ResultGenerator.genSuccess("增加班级课程计划成功"):ResultGenerator.genFailed("增加班级课程计划失败！");
    }

    @PostMapping("/edit") // 编辑班级课程计划
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody ClassCourseDto classCourseDto){
        ClassCourse classCourse = BeanCopyUtils.copyBean(classCourseDto, ClassCourse.class);
        return classCourseService.updateById(classCourse)? ResultGenerator.genSuccess("编辑班级课程计划成功"):ResultGenerator.genFailed("编辑班级课程计划失败！");
    }

    @PostMapping("/delete") // 删除班级课程计划
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<ClassCourseDto> ids){
        try {
            for(ClassCourseDto classCourseDto: ids) {
                Map<String, Object> map = new HashMap<>();
                map.put("class_id", classCourseDto.getClassId());
                map.put("course_id", classCourseDto.getCourseId());
                classCourseService.removeByMap(map);
            }
            return ResultGenerator.genSuccess("删除班级课程计划成功");
        } catch (Exception e) {
            return ResultGenerator.genFailed("删除班级课程计划失败！");
        }
    }

    @GetMapping("/item") // 查询班级课程计划
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
//        Classes classes = BeanCopyUtils.copyBean(classStudentDto, Classes.class);
        ClassCourse result = classCourseService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取班级课程计划成功",result):ResultGenerator.genFailed("获取班级课程计划失败！");

    }

    @GetMapping("/list") // 查询所有班级课程计划
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                             @NotNull @RequestParam(value = "page_size")Integer pageSize,
                             @RequestParam(value = "query_value")String queryValue,
                             @RequestParam(value = "class_id")Long classId){
        return classCourseService.getList(pageNum, pageSize, classId, queryValue);
    }
}
