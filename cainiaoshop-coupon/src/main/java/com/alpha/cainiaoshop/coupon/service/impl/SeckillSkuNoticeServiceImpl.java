package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SeckillSkuNoticeDao;
import com.alpha.cainiaoshop.coupon.entity.SeckillSkuNoticeEntity;
import com.alpha.cainiaoshop.coupon.service.SeckillSkuNoticeService;


@Service("seckillSkuNoticeService" )
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeDao, SeckillSkuNoticeEntity> implements SeckillSkuNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SeckillSkuNoticeEntity> list = this.list(new QueryWrapper<SeckillSkuNoticeEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}