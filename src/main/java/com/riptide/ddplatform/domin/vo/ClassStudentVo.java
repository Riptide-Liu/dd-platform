package com.riptide.ddplatform.domin.vo;

import com.riptide.ddplatform.domin.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassStudentVo {

    private Long id;
    /**
     * 班级名
     */
    private String name;
    private List<User> items;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
