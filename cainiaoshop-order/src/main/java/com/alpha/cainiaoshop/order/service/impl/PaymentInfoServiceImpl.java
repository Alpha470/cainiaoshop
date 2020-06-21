package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.PaymentInfoDao;
import com.alpha.cainiaoshop.order.entity.PaymentInfoEntity;
import com.alpha.cainiaoshop.order.service.PaymentInfoService;


@Service("paymentInfoService" )
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity> implements PaymentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<PaymentInfoEntity> list = this.list(new QueryWrapper<PaymentInfoEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}