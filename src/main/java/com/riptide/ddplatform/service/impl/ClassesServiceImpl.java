package com.riptide.ddplatform.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.mapper.ClassesMapper;
import com.riptide.ddplatform.service.ClassesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

}
