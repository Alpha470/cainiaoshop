package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SeckillSessionDao;
import com.alpha.cainiaoshop.coupon.entity.SeckillSessionEntity;
import com.alpha.cainiaoshop.coupon.service.SeckillSessionService;


@Service("seckillSessionService" )
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SeckillSessionEntity> list = this.list(new QueryWrapper<SeckillSessionEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}