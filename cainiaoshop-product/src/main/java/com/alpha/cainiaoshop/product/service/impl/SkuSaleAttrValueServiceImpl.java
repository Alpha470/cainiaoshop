package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.SkuSaleAttrValueDao;
import com.alpha.cainiaoshop.product.entity.SkuSaleAttrValueEntity;
import com.alpha.cainiaoshop.product.service.SkuSaleAttrValueService;


@Service("skuSaleAttrValueService" )
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SkuSaleAttrValueEntity> list = this.list(new QueryWrapper<SkuSaleAttrValueEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}