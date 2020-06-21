package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;

import com.alpha.cainiaoshop.product.dao.CategoryDao;
import com.alpha.cainiaoshop.product.entity.CategoryEntity;
import com.alpha.cainiaoshop.product.service.CategoryService;


@Service("categoryService" )
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        ArrayList<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return  paths.toArray(new Long[parentPath.size()]);
    }

    @Override
    public void updateDetail(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    private List<Long> findParentPath(Long catelogId, ArrayList<Long> paths) {
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = categoryDao.selectList(null);
        return categoryEntities;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CategoryEntity> list = this.list(new QueryWrapper<CategoryEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public List<CategoryEntity> queryTreelist() {
        return null;
    }



    @Override
    public void removeMenusByIds(List<Long> asList) {
        //todo 查看是否其他地方用到了
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 递归查找
     *
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> collect = all.stream().filter(item ->
                item.getParentCid() == root.getCatId())
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildens(categoryEntity, all));
                    return categoryEntity;
                }).sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return collect;
    }
}