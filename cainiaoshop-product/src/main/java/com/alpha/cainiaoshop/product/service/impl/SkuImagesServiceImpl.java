package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.SkuImagesDao;
import com.alpha.cainiaoshop.product.entity.SkuImagesEntity;
import com.alpha.cainiaoshop.product.service.SkuImagesService;


@Service("skuImagesService" )
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SkuImagesEntity> list = this.list(new QueryWrapper<SkuImagesEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}