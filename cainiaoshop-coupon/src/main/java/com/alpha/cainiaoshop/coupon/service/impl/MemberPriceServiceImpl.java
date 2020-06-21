package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.MemberPriceDao;
import com.alpha.cainiaoshop.coupon.entity.MemberPriceEntity;
import com.alpha.cainiaoshop.coupon.service.MemberPriceService;


@Service("memberPriceService" )
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberPriceEntity> list = this.list(new QueryWrapper<MemberPriceEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}