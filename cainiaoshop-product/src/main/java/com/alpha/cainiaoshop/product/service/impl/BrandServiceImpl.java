package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.controller.CategoryBrandRelationController;
import com.alpha.cainiaoshop.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.BrandDao;
import com.alpha.cainiaoshop.product.entity.BrandEntity;
import com.alpha.cainiaoshop.product.service.BrandService;
import org.springframework.util.StringUtils;


@Service("brandService" )
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)){
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }
        IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public void updateDetail(BrandEntity brand) {
        this.updateById(brand);

        if (!StringUtils.isEmpty(brand.getName())){
            //更新其他关联字段
            categoryBrandRelationService.updateName(brand.getBrandId(),brand.getName());
            //todo
        }
    }

}