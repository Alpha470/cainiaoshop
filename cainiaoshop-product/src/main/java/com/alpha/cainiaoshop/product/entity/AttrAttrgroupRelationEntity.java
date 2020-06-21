package com.alpha.cainiaoshop.product.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 属性&属性分组关联
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@Data
@TableName("pms_attr_attrgroup_relation" )
public class AttrAttrgroupRelationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性分组id
     */
    private Long attrGroupId;
    /**
     * 属性组内排序
     */
    private Integer attrSort;

}
