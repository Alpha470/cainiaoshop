package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SpuBoundsDao;
import com.alpha.cainiaoshop.coupon.entity.SpuBoundsEntity;
import com.alpha.cainiaoshop.coupon.service.SpuBoundsService;


@Service("spuBoundsService" )
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SpuBoundsEntity> list = this.list(new QueryWrapper<SpuBoundsEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}