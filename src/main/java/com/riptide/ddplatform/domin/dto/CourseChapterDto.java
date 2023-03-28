package com.riptide.ddplatform.domin.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CourseChapterDto implements Serializable {
    @NotNull(message = "Id不能为空", groups = {ValidatorGroups.Update.class})
    private Long id;
    @NotBlank(message = "章节名不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String name;
    @NotBlank(message = "课程Id不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private Long courseId;
}
