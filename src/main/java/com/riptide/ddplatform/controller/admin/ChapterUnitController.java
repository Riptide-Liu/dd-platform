package com.riptide.ddplatform.controller.admin;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.dto.ChapterUnitDto;
import com.riptide.ddplatform.domin.dto.ValidatorGroups;
import com.riptide.ddplatform.domin.pojo.ChapterUnit;
import com.riptide.ddplatform.service.admin.ChapterUnitService;
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
@Api("课程章节单元模块")
@RequestMapping("/admin/chapter/unit")
public class ChapterUnitController {

    @Autowired
    private ChapterUnitService chapterUnitService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult add(@Validated(value = ValidatorGroups.Add.class) @RequestBody ChapterUnitDto chapterUnitDto){
        ChapterUnit chapterUnit = BeanCopyUtils.copyBean(chapterUnitDto, ChapterUnit.class);
        return chapterUnitService.save(chapterUnit)? ResultGenerator.genSuccess("增加成功"):ResultGenerator.genFailed("增加失败！");
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult edit(@Validated(value = ValidatorGroups.Update.class) @RequestBody ChapterUnitDto chapterUnitDto){
        ChapterUnit chapterUnit = BeanCopyUtils.copyBean(chapterUnitDto, ChapterUnit.class);
        return chapterUnitService.updateById(chapterUnit)? ResultGenerator.genSuccess("编辑成功"):ResultGenerator.genFailed("编辑失败！");
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
    public APIResult delete(@RequestBody List<Long> ids){
        return chapterUnitService.removeByIds(ids)? ResultGenerator.genSuccess("删除成功"):ResultGenerator.genFailed("删除失败！");
    }

    @GetMapping("/item")
    public APIResult getItem(@NotNull @RequestParam(value = "id") Long id){
        ChapterUnit result = chapterUnitService.getById(id);
        return !Objects.isNull(result)? ResultGenerator.genSuccess("获取成功",result):ResultGenerator.genFailed("获取失败！");

    }
}
