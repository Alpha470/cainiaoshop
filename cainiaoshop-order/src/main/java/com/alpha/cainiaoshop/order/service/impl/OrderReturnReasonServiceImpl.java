package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderReturnReasonDao;
import com.alpha.cainiaoshop.order.entity.OrderReturnReasonEntity;
import com.alpha.cainiaoshop.order.service.OrderReturnReasonService;


@Service("orderReturnReasonService" )
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderReturnReasonEntity> list = this.list(new QueryWrapper<OrderReturnReasonEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}