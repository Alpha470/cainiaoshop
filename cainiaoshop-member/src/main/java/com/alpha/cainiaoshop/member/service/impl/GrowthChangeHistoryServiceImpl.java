package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.GrowthChangeHistoryDao;
import com.alpha.cainiaoshop.member.entity.GrowthChangeHistoryEntity;
import com.alpha.cainiaoshop.member.service.GrowthChangeHistoryService;


@Service("growthChangeHistoryService" )
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity> implements GrowthChangeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<GrowthChangeHistoryEntity> list = this.list(new QueryWrapper<GrowthChangeHistoryEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}