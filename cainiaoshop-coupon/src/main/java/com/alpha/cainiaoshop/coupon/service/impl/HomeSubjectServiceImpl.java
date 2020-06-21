package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.HomeSubjectDao;
import com.alpha.cainiaoshop.coupon.entity.HomeSubjectEntity;
import com.alpha.cainiaoshop.coupon.service.HomeSubjectService;


@Service("homeSubjectService" )
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectDao, HomeSubjectEntity> implements HomeSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<HomeSubjectEntity> list = this.list(new QueryWrapper<HomeSubjectEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}