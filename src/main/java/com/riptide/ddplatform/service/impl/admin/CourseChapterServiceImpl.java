package com.riptide.ddplatform.service.impl.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.*;
import com.riptide.ddplatform.domin.vo.CourseChapterVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.ChapterUnitMapper;
import com.riptide.ddplatform.mapper.CourseChapterMapper;
import com.riptide.ddplatform.service.admin.CourseChapterService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterMapper, CourseChapter> implements CourseChapterService {

    @Autowired
    private ChapterUnitMapper chapterUnitMapper;

    @Autowired
    private CourseChapterMapper courseChapterMapper;

    @Override
    public APIResult getList(Long courseId) {
        //查询条件
        LambdaQueryWrapper<CourseChapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据courseId查询
        lambdaQueryWrapper.eq(courseId != null , CourseChapter::getCourseId,  courseId);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(CourseChapter::getCreateTime);

        //分页查询
      /*  Page<CourseChapter> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);*/

        List<CourseChapter> courseChapters = courseChapterMapper.selectList(lambdaQueryWrapper);


        //封装查询结果
        List<CourseChapterVo> courseChapterVos = BeanCopyUtils.copyBeanList(courseChapters, CourseChapterVo.class);
       for(CourseChapterVo chapterVo: courseChapterVos){
           //查询条件
           LambdaQueryWrapper<ChapterUnit> unitWrapper = new LambdaQueryWrapper<>();
           // 对创建时间进行降序
           unitWrapper.orderByAsc(ChapterUnit::getCreateTime);
           // 根据id查找
           unitWrapper.eq(ChapterUnit::getChapterId,chapterVo.getId());
           List<ChapterUnit> chapterUnitList = chapterUnitMapper.selectList(unitWrapper);
           chapterVo.setUnit(chapterUnitList);
       }


//        PageVo pageVo = new PageVo(courseChapterVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取章节列表成功", courseChapterVos);
    }
}
