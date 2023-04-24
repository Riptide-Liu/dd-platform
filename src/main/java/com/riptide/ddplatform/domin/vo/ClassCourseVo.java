package com.riptide.ddplatform.domin.vo;

import com.riptide.ddplatform.domin.pojo.Classes;
import com.riptide.ddplatform.domin.pojo.Course;
import com.riptide.ddplatform.domin.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCourseVo {

    private Long id;
    private Classes classes;
    private Course course;
    private Date startTime;
    private Date endTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
