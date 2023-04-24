package com.riptide.ddplatform.controller;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.CourseResourceDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.CourseResource;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.service.admin.CourseResourceService;
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
@RequestMapping("/course/resource")
@Api(tags = "课程资源模块",description = "课程资源相关接口")
public class UserCourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@RequestParam(value = "course_id")Long courseId) {
        return courseResourceService.getALL(courseId);
    }
}
