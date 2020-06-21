package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderSettingDao;
import com.alpha.cainiaoshop.order.entity.OrderSettingEntity;
import com.alpha.cainiaoshop.order.service.OrderSettingService;


@Service("orderSettingService" )
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingDao, OrderSettingEntity> implements OrderSettingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderSettingEntity> list = this.list(new QueryWrapper<OrderSettingEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}