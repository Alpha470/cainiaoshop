package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderItemDao;
import com.alpha.cainiaoshop.order.entity.OrderItemEntity;
import com.alpha.cainiaoshop.order.service.OrderItemService;


@Service("orderItemService" )
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderItemEntity> list = this.list(new QueryWrapper<OrderItemEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}