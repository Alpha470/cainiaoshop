package com.alpha.cainiaoshop.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class MergeVo {
    private List<Long> items;// [1, 2]
    private Long purchaseId;//: 1
}
