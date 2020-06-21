package com.alpha.cainiaoshop.coupon.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.HomeSubjectSpuDao;
import com.alpha.cainiaoshop.coupon.entity.HomeSubjectSpuEntity;
import com.alpha.cainiaoshop.coupon.service.HomeSubjectSpuService;


@Service("homeSubjectSpuService" )
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<HomeSubjectSpuEntity> list = this.list(new QueryWrapper<HomeSubjectSpuEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}