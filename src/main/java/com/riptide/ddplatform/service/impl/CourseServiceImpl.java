package com.riptide.ddplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Course;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.domin.vo.CourseVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.CourseMapper;
import com.riptide.ddplatform.service.CourseService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public APIResult getCourseList(Integer pageNum, Integer pageSize, String queryValue) {
        //查询条件
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(Course::getCreateTime);
        // 模糊查询
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryValue) , Course::getName,  queryValue);
        //分页查询
        Page<Course> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //封装查询结果
        List<CourseVo> courseVos = BeanCopyUtils.copyBeanList(page.getRecords(), CourseVo.class);

        PageVo pageVo = new PageVo(courseVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取课程列表成功",pageVo);
    }
}
