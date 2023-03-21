package com.riptide.ddplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.pojo.User;
import com.riptide.ddplatform.domin.vo.ClassVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.domin.vo.UserVo;
import com.riptide.ddplatform.mapper.ClassesMapper;
import com.riptide.ddplatform.service.ClassesService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Override
    public APIResult getClassList(Integer pageNum, Integer pageSize) {
        //查询条件
        LambdaQueryWrapper<Classes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态是未删除的
        lambdaQueryWrapper.eq(Classes::getDelFlag, 0);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(Classes::getCreateTime);

        //分页查询
        Page<Classes> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //封装查询结果
        List<ClassVo> classVos = BeanCopyUtils.copyBeanList(page.getRecords(), ClassVo.class);

        PageVo pageVo = new PageVo(classVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取班级列表成功",pageVo);
    }
}
