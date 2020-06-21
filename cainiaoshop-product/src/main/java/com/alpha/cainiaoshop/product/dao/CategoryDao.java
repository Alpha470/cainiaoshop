package com.alpha.cainiaoshop.product.dao;

import com.alpha.cainiaoshop.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
