package com.alpha.cainiaoshop.product.dao;

import com.alpha.cainiaoshop.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBathRelation(@Param("entities") List<AttrAttrgroupRelationEntity> relationEntities);

}
