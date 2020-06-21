package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderDao;
import com.alpha.cainiaoshop.order.entity.OrderEntity;
import com.alpha.cainiaoshop.order.service.OrderService;


@Service("orderService" )
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderEntity> list = this.list(new QueryWrapper<OrderEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}