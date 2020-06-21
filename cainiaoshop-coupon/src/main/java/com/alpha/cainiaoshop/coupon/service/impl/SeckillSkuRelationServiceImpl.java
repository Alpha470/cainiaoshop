package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SeckillSkuRelationDao;
import com.alpha.cainiaoshop.coupon.entity.SeckillSkuRelationEntity;
import com.alpha.cainiaoshop.coupon.service.SeckillSkuRelationService;


@Service("seckillSkuRelationService" )
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity> implements SeckillSkuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SeckillSkuRelationEntity> list = this.list(new QueryWrapper<SeckillSkuRelationEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}