package com.riptide.ddplatform.domin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseChapterVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 章节名
     */
    private String name;
    /**
     * 单元列表
     */
    private List items;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
