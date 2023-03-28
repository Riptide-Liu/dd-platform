package com.riptide.ddplatform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.ChapterUnit;
import com.riptide.ddplatform.domin.pojo.CourseChapter;
import com.riptide.ddplatform.domin.vo.CourseChapterVo;
import com.riptide.ddplatform.domin.vo.PageVo;
import com.riptide.ddplatform.mapper.ChapterUnitMapper;
import com.riptide.ddplatform.mapper.CourseChapterMapper;
import com.riptide.ddplatform.service.ChapterUnitService;
import com.riptide.ddplatform.service.CourseChapterService;
import com.riptide.ddplatform.util.BeanCopyUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChapterUnitServiceImpl extends ServiceImpl<ChapterUnitMapper, ChapterUnit> implements ChapterUnitService {
}
