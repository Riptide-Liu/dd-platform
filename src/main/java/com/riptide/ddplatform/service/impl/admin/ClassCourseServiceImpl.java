package com.riptide.ddplatform.service.impl.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.*;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.domin.vo.ClassCourseVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.*;
import com.riptide.ddplatform.mapper.ClassCourseMapper;
import com.riptide.ddplatform.service.admin.ClassCourseService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClassCourseServiceImpl extends ServiceImpl<ClassCourseMapper, ClassCourse> implements ClassCourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public APIResult getList(Integer pageNum, Integer pageSize, Long classId, String queryValue) {
        //查询条件
        LambdaQueryWrapper<ClassCourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据classId查询
        lambdaQueryWrapper.eq(classId != null , ClassCourse::getClassId,  classId);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(ClassCourse::getCreateTime);

        //分页查询
        Page<ClassCourse> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<ClassCourse> records = page.getRecords();
        List<ClassCourseVo> vos = new ArrayList<>();
        Classes classes = classesMapper.selectById(classId);
        //封装查询结果
        if(!records.isEmpty()) {
            for(ClassCourse classCourse: records){
                ClassCourseVo vo = BeanCopyUtils.copyBean(classCourse, ClassCourseVo.class);
                vo.setClasses(classes);
                vo.setCourse(courseMapper.selectById(classCourse.getCourseId()));
                vos.add(vo);
            }
        }

        PageVo pageVo = new PageVo(vos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取班级课程计划列表成功",pageVo);
    }
}
