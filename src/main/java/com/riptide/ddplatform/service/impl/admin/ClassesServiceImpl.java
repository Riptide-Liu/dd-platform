package com.riptide.ddplatform.service.impl.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.vo.ClassVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.ClassesMapper;
import com.riptide.ddplatform.service.admin.ClassesService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public APIResult getClassList(Integer pageNum, Integer pageSize, String queryValue) {
        //查询条件
        LambdaQueryWrapper<Classes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态是未删除的
        lambdaQueryWrapper.eq(Classes::getDelFlag, 0);
        // 对创建时间进行降序
        lambdaQueryWrapper.orderByDesc(Classes::getCreateTime);
        // 模糊查询
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryValue) , Classes::getName,  queryValue);
        //分页查询
        Page<Classes> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        //封装查询结果
        List<ClassVo> classVos = BeanCopyUtils.copyBeanList(page.getRecords(), ClassVo.class);

        PageVo pageVo = new PageVo(classVos,page.getTotal(), page.getSize(), page.getCurrent());
        return ResultGenerator.genSuccess("获取班级列表成功",pageVo);
    }
}
