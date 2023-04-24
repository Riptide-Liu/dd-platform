package com.riptide.ddplatform.controller;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ClassStudentDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.ClassStudent;
import com.riptide.ddplatform.service.admin.ClassStudentService;
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
@Api("班级学生模块")
@RequestMapping("/class/student")
public class UserClassStudentController {

    @Autowired
    private ClassStudentService classStudentService;

    @GetMapping("/list") // 查询同班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(){
        return classStudentService.getAll();
    }
}
