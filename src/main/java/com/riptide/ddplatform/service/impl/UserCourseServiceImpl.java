package com.riptide.ddplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.LoginUser;
import com.riptide.ddplatform.domin.pojo.ClassCourse;
import com.riptide.ddplatform.domin.pojo.ClassStudent;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.vo.ClassCourseVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.ClassCourseMapper;
import com.riptide.ddplatform.mapper.ClassStudentMapper;
import com.riptide.ddplatform.mapper.ClassesMapper;
import com.riptide.ddplatform.mapper.CourseMapper;
import com.riptide.ddplatform.service.UserCourseService;
import com.riptide.ddplatform.service.admin.ClassCourseService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserCourseServiceImpl extends ServiceImpl<ClassCourseMapper, ClassCourse> implements UserCourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private ClassStudentMapper classStudentMapper;
    @Override
    public APIResult getList(Integer pageNum, Integer pageSize, String queryValue) {
        // 获取当前用户的课程
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //查询条件
        LambdaQueryWrapper<ClassCourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        LambdaQueryWrapper<ClassStudent> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(ClassStudent::getUserId, userid);
        ClassStudent classStudent = classStudentMapper.selectOne(studentWrapper);

        if(Objects.isNull(classStudent)) {
            return ResultGenerator.genSuccess("获取课程列表成功",new ArrayList<>());
        }
        // 根据classId查询
        lambdaQueryWrapper.eq(ClassCourse::getClassId,  classStudent.getClassId());
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(ClassCourse::getCreateTime);

        //分页查询
        Page<ClassCourse> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<ClassCourse> records = page.getRecords();
        List<ClassCourseVo> vos = new ArrayList<>();
        Classes classes = classesMapper.selectById(classStudent.getClassId());
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
        return ResultGenerator.genSuccess("获取课程列表成功",pageVo);
    }
}
