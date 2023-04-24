package com.riptide.ddplatform.service.impl.admin;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.pojo.ChapterUnit;
import com.riptide.ddplatform.mapper.ChapterUnitMapper;
import com.riptide.ddplatform.service.admin.ChapterUnitService;
import org.springframework.stereotype.Service;


@Service
public class ChapterUnitServiceImpl extends ServiceImpl<ChapterUnitMapper, ChapterUnit> implements ChapterUnitService {
}
