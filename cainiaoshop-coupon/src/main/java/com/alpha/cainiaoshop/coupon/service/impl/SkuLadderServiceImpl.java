package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SkuLadderDao;
import com.alpha.cainiaoshop.coupon.entity.SkuLadderEntity;
import com.alpha.cainiaoshop.coupon.service.SkuLadderService;


@Service("skuLadderService" )
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SkuLadderEntity> list = this.list(new QueryWrapper<SkuLadderEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}