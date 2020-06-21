package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.CouponSpuCategoryRelationDao;
import com.alpha.cainiaoshop.coupon.entity.CouponSpuCategoryRelationEntity;
import com.alpha.cainiaoshop.coupon.service.CouponSpuCategoryRelationService;


@Service("couponSpuCategoryRelationService" )
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements CouponSpuCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CouponSpuCategoryRelationEntity> list = this.list(new QueryWrapper<CouponSpuCategoryRelationEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}