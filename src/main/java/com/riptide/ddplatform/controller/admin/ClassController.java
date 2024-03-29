package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ClassesDto;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.service.admin.ClassesService;
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
@Api("班级模块")
@RequestMapping("/admin/class")
public class ClassController {

    @Autowired
    private ClassesService classesService;

    @PostMapping("/add") // 增加班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody ClassesDto classesDto){
        Classes classes = BeanCopyUtils.copyBean(classesDto, Classes.class);
        return classesService.save(classes)? ResultGenerator.genSuccess("增加班级成功"):ResultGenerator.genFailed("增加班级失败！");
    }

    @PostMapping("/edit") // 编辑班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody ClassesDto classesDto){
        Classes classes = BeanCopyUtils.copyBean(classesDto, Classes.class);
        return classesService.updateById(classes)? ResultGenerator.genSuccess("编辑班级成功"):ResultGenerator.genFailed("编辑班级失败！");
    }

    @PostMapping("/delete") // 删除班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<Long> ids){
        return classesService.removeByIds(ids)? ResultGenerator.genSuccess("删除班级成功"):ResultGenerator.genFailed("删除班级失败！");
    }

    @GetMapping("/item") // 查询班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
//        Classes classes = BeanCopyUtils.copyBean(classesDto, Classes.class);
        Classes result = classesService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取班级成功",result):ResultGenerator.genFailed("获取班级失败！");

    }

    @GetMapping("/list/all") // 查询所有班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(){
        List<Classes> list = classesService.list();
        return ResultGenerator.genSuccess("获取班级成功",list);
    }

    @GetMapping("/list") // 查询所有班级
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,@NotNull @RequestParam(value = "page_size")Integer pageSize,  @RequestParam(value = "query_value")String queryValue){
        return classesService.getClassList(pageNum, pageSize, queryValue);
    }
}
