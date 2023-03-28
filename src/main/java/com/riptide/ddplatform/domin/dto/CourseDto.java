package com.riptide.ddplatform.domin.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.w3c.dom.Text;

import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "Id不能为空", groups = {ValidatorGroups.Update.class})
    private Long id;
    /**
     * 课程名
     */
    @NotBlank(message = "课程名不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String name;
    /**
     * 封面图片
     */
    private String image_uuid;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
