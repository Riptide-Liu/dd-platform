package com.riptide.ddplatform.controller.admin;

import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.CourseChapterDto;
import com.riptide.ddplatform.domin.dto.CourseResourceDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import com.riptide.ddplatform.domin.pojo.CourseResource;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.service.CourseResourceService;
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
@RequestMapping("/admin/course/resource")
@Api(tags = "课程资源模块",description = "课程资源相关接口")
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody CourseResourceDto courseResourceDto){
        CourseResource courseResource = BeanCopyUtils.copyBean(courseResourceDto, CourseResource.class);
        return courseResourceService.save(courseResource)? ResultGenerator.genSuccess(ApiEnum.ADD_SUCCESS):ResultGenerator.genFailed(ApiEnum.ADD_FAILED);
    }



    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody  CourseResourceDto courseResourceDto){
        CourseResource courseResource = BeanCopyUtils.copyBean(courseResourceDto, CourseResource.class);
        return courseResourceService.updateById(courseResource)? ResultGenerator.genSuccess(ApiEnum.EDIT_SUCCESS):ResultGenerator.genFailed(ApiEnum.EDIT_FAILED);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<Long> ids){
        return courseResourceService.removeByIds(ids)? ResultGenerator.genSuccess(ApiEnum.DELETE_SUCCESS):ResultGenerator.genFailed(ApiEnum.DELETE_FAILED);
    }

    @GetMapping("/item")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
        CourseResource result = courseResourceService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess(ApiEnum.GET_SUCCESS,result):ResultGenerator.genFailed(ApiEnum.GET_FAILED);

    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult getList(@NotNull @RequestParam(value = "page_num")Integer pageNum,@NotNull @RequestParam(value = "page_size")Integer pageSize){
        return courseResourceService.getList(pageNum, pageSize);
    }
}
