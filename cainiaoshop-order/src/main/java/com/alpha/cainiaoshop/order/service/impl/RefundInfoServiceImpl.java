package com.alpha.cainiaoshop.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.order.dao.RefundInfoDao;
import com.alpha.cainiaoshop.order.entity.RefundInfoEntity;
import com.alpha.cainiaoshop.order.service.RefundInfoService;


@Service("refundInfoService" )
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<RefundInfoEntity> list = this.list(new QueryWrapper<RefundInfoEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}