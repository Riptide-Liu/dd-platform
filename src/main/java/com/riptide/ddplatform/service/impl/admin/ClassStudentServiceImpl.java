package com.riptide.ddplatform.service.impl.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.LoginUser;
import com.riptide.ddplatform.domin.pojo.*;
import com.riptide.ddplatform.domin.vo.ClassCourseVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.domin.vo.UserVo;
import com.riptide.ddplatform.mapper.*;
import com.riptide.ddplatform.service.admin.ClassStudentService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClassStudentServiceImpl extends ServiceImpl<ClassStudentMapper, ClassStudent> implements ClassStudentService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private ClassStudentMapper classStudentMapper;
    @Override
    public APIResult getList(Integer pageNum, Integer pageSize, Long classId, String queryValue) {
        //查询条件
        LambdaQueryWrapper<ClassStudent> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        classesMapper.selectById(classId)
        // 根据classId查询
        lambdaQueryWrapper.eq(classId != null , ClassStudent::getClassId,  classId);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(ClassStudent::getCreateTime);

        //分页查询
        Page<ClassStudent> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<ClassStudent> records = page.getRecords();

        //封装查询结果
        List<UserVo> userVos = new ArrayList<>();

        if(!records.isEmpty()){
            List<Long> userIds = new ArrayList<>();
            for(ClassStudent classStudent: records){
                userIds.add(classStudent.getUserId());
            }
            List<User> userList = userMapper.selectBatchIds(userIds);
            userVos = BeanCopyUtils.copyBeanList(userList, UserVo.class);
        }

//        Classes classes = classesMapper.selectById(classId);


        PageVo pageVo = new PageVo(userVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取班级学生列表成功",pageVo);
    }

    @Override
    public APIResult getAll() {
        // 获取当前班级的所有用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();


        LambdaQueryWrapper<ClassStudent> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(ClassStudent::getUserId, userid);
        // 先根据当前用户查到所属班级
        ClassStudent classStudent = classStudentMapper.selectOne(studentWrapper);

        // 再根据班级id获取所有属于当前班级的用户
        LambdaQueryWrapper<ClassStudent> ssw = new LambdaQueryWrapper<>();
        ssw.eq(ClassStudent::getClassId, classStudent.getClassId());
        List<ClassStudent> classStudents = classStudentMapper.selectList(ssw);

        List<Long> userIds = new ArrayList<>();
        for(ClassStudent cs: classStudents) {
            userIds.add(cs.getUserId());
        }

        List<User> users = userMapper.selectBatchIds(userIds);

        return ResultGenerator.genSuccess("获取同班同学成功",users);
    }
}
