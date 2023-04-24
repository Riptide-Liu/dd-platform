package com.riptide.ddplatform.domin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassStudentDto implements Serializable {

    @NotNull(message = "班级ID不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Long classId;

    @NotNull(message = "用户ID不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private Long userId;
}
