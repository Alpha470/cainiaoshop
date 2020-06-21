package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.CouponDao;
import com.alpha.cainiaoshop.coupon.entity.CouponEntity;
import com.alpha.cainiaoshop.coupon.service.CouponService;


@Service("couponService" )
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CouponEntity> list = this.list(new QueryWrapper<CouponEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}