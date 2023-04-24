package com.riptide.ddplatform.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.domin.pojo.ClassStudent;
import com.riptide.ddplatform.domin.pojo.Classes;
import org.apache.ibatis.annotations.Param;


public interface ClassStudentService extends IService<ClassStudent> {
    APIResult getList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("classId") Long classId, @Param("queryValue") String queryValue);
    APIResult getAll();
}
