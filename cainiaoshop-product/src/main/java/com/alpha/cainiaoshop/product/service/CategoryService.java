package com.alpha.cainiaoshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
public interface CategoryService extends IService<CategoryEntity> {

    List<CategoryEntity> listWithTree();

    PageUtils queryPage(Map<String, Object> params);

    public List<CategoryEntity> queryTreelist();

    public void removeMenusByIds(List<Long> asList);

    public Long[] findCatelogPath(Long catelogId);

    public void updateDetail(CategoryEntity category);

}

