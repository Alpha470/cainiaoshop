package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.dao.BrandDao;
import com.alpha.cainiaoshop.product.dao.CategoryDao;
import com.alpha.cainiaoshop.product.entity.BrandEntity;
import com.alpha.cainiaoshop.product.entity.CategoryEntity;
import com.alpha.cainiaoshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.CategoryBrandRelationDao;
import com.alpha.cainiaoshop.product.entity.CategoryBrandRelationEntity;
import com.alpha.cainiaoshop.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService" )
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    BrandDao brandDao;
    @Autowired
    CategoryBrandRelationDao relationDao;
    @Autowired
    BrandService brandService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CategoryBrandRelationEntity> list = this.list(new QueryWrapper<CategoryBrandRelationEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long catelogId = categoryBrandRelation.getCatelogId();
        Long brandId = categoryBrandRelation.getBrandId();
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        BrandEntity brandEntitie = brandDao.selectById(brandId);

        categoryBrandRelation.setBrandName(brandEntitie.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }

    @Override
    public void updateName(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(name);
        categoryBrandRelationEntity.setBrandId(brandId);

        this.update(categoryBrandRelationEntity,new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);
    }

    @Override
    public List<BrandEntity> getBrandsCatId(Long catId) {
        List<CategoryBrandRelationEntity> catelogs = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<BrandEntity> collect = catelogs.stream().map(item -> {
            Long brandId = item.getBrandId();
            BrandEntity byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());
        return collect;
    }

}