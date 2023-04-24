package com.riptide.ddplatform.domin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCourseDto implements Serializable {

    @NotNull(message = "班级ID不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Long classId;

    @NotNull(message = "课程ID不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Long courseId;

    @NotNull(message = "开始时间不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Date startTime;

    @NotNull(message = "结束时间不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Date endTime;
}
