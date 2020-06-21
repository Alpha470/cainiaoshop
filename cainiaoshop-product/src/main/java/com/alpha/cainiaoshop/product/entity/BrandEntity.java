package com.alpha.cainiaoshop.product.entity;

import com.alpha.common.valid.Addgroup;
import com.alpha.common.valid.Editgroup;
import com.alpha.common.valid.ListVaLue;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@Data
@TableName("pms_brand" )
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @Null(message = "品牌id必须为空",groups = {Addgroup.class})
    @NotNull(message = "品牌id不能为空",groups = {Editgroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空")
    private String name;
    /**
     * 品牌logo地址
     */
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListVaLue(vals={1,0} ,groups = {Addgroup.class},message = "显示状态错误")
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty
    @Pattern(regexp = "/^[a-zA-Z]$/",message = "检索首字母只能是一个字母")
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull
    private Integer sort;

}
