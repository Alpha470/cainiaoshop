package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.CouponHistoryDao;
import com.alpha.cainiaoshop.coupon.entity.CouponHistoryEntity;
import com.alpha.cainiaoshop.coupon.service.CouponHistoryService;


@Service("couponHistoryService" )
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryDao, CouponHistoryEntity> implements CouponHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CouponHistoryEntity> list = this.list(new QueryWrapper<CouponHistoryEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}