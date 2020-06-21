package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderOperateHistoryDao;
import com.alpha.cainiaoshop.order.entity.OrderOperateHistoryEntity;
import com.alpha.cainiaoshop.order.service.OrderOperateHistoryService;


@Service("orderOperateHistoryService" )
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity> implements OrderOperateHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderOperateHistoryEntity> list = this.list(new QueryWrapper<OrderOperateHistoryEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}