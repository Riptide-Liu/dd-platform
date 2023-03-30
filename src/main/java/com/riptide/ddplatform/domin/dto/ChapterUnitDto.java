package com.riptide.ddplatform.domin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterUnitDto implements Serializable {
    @NotNull(message = "Id不能为空", groups = {ValidatorGroups.Update.class})
    private Long id;
    @NotBlank(message = "章节名不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private String name;
    @NotBlank(message = "章节Id不能为空", groups = {ValidatorGroups.Update.class, ValidatorGroups.Add.class})
    private Long chapterId;
    private String content;
    private String file_key;
}
