package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.OrderReturnApplyDao;
import com.alpha.cainiaoshop.order.entity.OrderReturnApplyEntity;
import com.alpha.cainiaoshop.order.service.OrderReturnApplyService;


@Service("orderReturnApplyService" )
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyDao, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<OrderReturnApplyEntity> list = this.list(new QueryWrapper<OrderReturnApplyEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}