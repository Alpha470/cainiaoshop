package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SeckillPromotionDao;
import com.alpha.cainiaoshop.coupon.entity.SeckillPromotionEntity;
import com.alpha.cainiaoshop.coupon.service.SeckillPromotionService;


@Service("seckillPromotionService" )
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity> implements SeckillPromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SeckillPromotionEntity> list = this.list(new QueryWrapper<SeckillPromotionEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}