package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.HomeAdvDao;
import com.alpha.cainiaoshop.coupon.entity.HomeAdvEntity;
import com.alpha.cainiaoshop.coupon.service.HomeAdvService;


@Service("homeAdvService" )
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvDao, HomeAdvEntity> implements HomeAdvService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<HomeAdvEntity> list = this.list(new QueryWrapper<HomeAdvEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}