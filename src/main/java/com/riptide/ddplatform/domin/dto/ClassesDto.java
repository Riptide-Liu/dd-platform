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
public class ClassesDto implements Serializable {

    @NotNull(message = "Id不能为空", groups = {ValidatorGroups.SelectItem.class, ValidatorGroups.Delete.class, ValidatorGroups.Update.class})
    private Long id;

    @NotBlank(message = "班级名不能为空", groups = {ValidatorGroups.Add.class, ValidatorGroups.Update.class})
    private String name;
}
