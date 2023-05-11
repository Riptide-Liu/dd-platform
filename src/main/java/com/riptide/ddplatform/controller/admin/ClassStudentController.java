package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ClassStudentDto;
import com.riptide.ddplatform.domin.pojo.ClassStudent;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
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
@RequestMapping("/admin/class/student")
public class ClassStudentController {

    @Autowired
    private ClassStudentService classStudentService;

    @PostMapping("/add") // 增加班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody ClassStudentDto classStudentDto){
        ClassStudent classStudent = BeanCopyUtils.copyBean(classStudentDto, ClassStudent.class);
        return classStudentService.add(classStudent);
//                ? ResultGenerator.genSuccess("增加班级学生成功"):ResultGenerator.genFailed("增加班级学生失败！")
    }

    @PostMapping("/edit") // 编辑班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody ClassStudentDto classStudentDto){
        ClassStudent classStudent = BeanCopyUtils.copyBean(classStudentDto, ClassStudent.class);
        return classStudentService.updateById(classStudent)? ResultGenerator.genSuccess("编辑班级学生成功"):ResultGenerator.genFailed("编辑班级学生失败！");
    }

    @PostMapping("/delete") // 删除班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<ClassStudentDto> ids){
        try {
            for(ClassStudentDto classStudentDto: ids) {
                Map<String, Object> map = new HashMap<>();
                map.put("class_id", classStudentDto.getClassId());
                map.put("user_id", classStudentDto.getUserId());
                classStudentService.removeByMap(map);
            }
            return ResultGenerator.genSuccess("删除班级学生成功");
        } catch (Exception e) {
            return ResultGenerator.genFailed("删除班级学生失败！");
        }

    }

    @GetMapping("/item") // 查询班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
//        Classes classes = BeanCopyUtils.copyBean(classStudentDto, Classes.class);
        ClassStudent result = classStudentService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取班级学生成功",result):ResultGenerator.genFailed("获取班级学生失败！");

    }

    @GetMapping("/list") // 查询所有班级学生
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,
                             @NotNull @RequestParam(value = "page_size")Integer pageSize,
                             @RequestParam(value = "query_value")String queryValue,
                             @RequestParam(value = "class_id")Long classId){
        return classStudentService.getList(pageNum, pageSize, classId, queryValue);
    }
}
