package com.riptide.ddplatform.domin.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResourceDto implements Serializable {
    @NotNull(message = "id不能为空", groups = {ValidatorGroups.Update.class})
    private Long id;

    @NotBlank(message = "课程资源名不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String name;
    @NotBlank(message = "文件不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String fileKey;

    @NotNull(message = "课程id不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private Long courseId;

}
