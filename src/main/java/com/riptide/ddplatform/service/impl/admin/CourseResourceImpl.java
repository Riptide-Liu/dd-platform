package com.riptide.ddplatform.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.CourseResource;
import com.riptide.ddplatform.domin.vo.CourseResourceVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.CourseResourceMapper;
import com.riptide.ddplatform.service.admin.CourseResourceService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceImpl extends ServiceImpl<CourseResourceMapper, CourseResource> implements CourseResourceService {

    @Autowired
    private CourseResourceMapper courseResourceMapper;
    @Override
    public APIResult getList(Integer pageNum, Integer pageSize, Long courseId, String queryValue) {
        //查询条件
        LambdaQueryWrapper<CourseResource> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据courseId查询
        lambdaQueryWrapper.eq(courseId != null , CourseResource::getCourseId,  courseId);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(CourseResource::getCreateTime);
        // 模糊查询
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryValue) , CourseResource::getName,  queryValue);
        //分页查询
        Page<CourseResource> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //封装查询结果
        List<CourseResourceVo> courseResourceVoList = BeanCopyUtils.copyBeanList(page.getRecords(), CourseResourceVo.class);


        PageVo pageVo = new PageVo(courseResourceVoList,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取课程资源列表成功",pageVo);
    }

    @Override
    public APIResult getALL(Long courseId) {
        //查询条件
        LambdaQueryWrapper<CourseResource> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据courseId查询
        lambdaQueryWrapper.eq(courseId != null , CourseResource::getCourseId,  courseId);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(CourseResource::getCreateTime);

        List<CourseResource> courseResources = courseResourceMapper.selectList(lambdaQueryWrapper);

        //封装查询结果
        List<CourseResourceVo> courseResourceVoList = BeanCopyUtils.copyBeanList(courseResources, CourseResourceVo.class);

        return ResultGenerator.genSuccess("获取课程资源列表成功",courseResourceVoList);
    }
}
