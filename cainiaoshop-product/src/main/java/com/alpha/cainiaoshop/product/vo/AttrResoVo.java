package com.alpha.cainiaoshop.product.vo;


import lombok.Data;

@Data
public class AttrResoVo extends  AttrVo {

    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}
