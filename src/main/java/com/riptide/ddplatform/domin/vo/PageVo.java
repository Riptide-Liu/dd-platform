package com.riptide.ddplatform.domin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    private List items;
    private Long total;
    private Long pageSize;
    private Long pageNum;
}
