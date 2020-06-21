package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.CouponSpuRelationDao;
import com.alpha.cainiaoshop.coupon.entity.CouponSpuRelationEntity;
import com.alpha.cainiaoshop.coupon.service.CouponSpuRelationService;


@Service("couponSpuRelationService" )
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity> implements CouponSpuRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CouponSpuRelationEntity> list = this.list(new QueryWrapper<CouponSpuRelationEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}