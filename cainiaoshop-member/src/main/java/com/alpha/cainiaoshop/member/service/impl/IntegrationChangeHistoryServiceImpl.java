package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.IntegrationChangeHistoryDao;
import com.alpha.cainiaoshop.member.entity.IntegrationChangeHistoryEntity;
import com.alpha.cainiaoshop.member.service.IntegrationChangeHistoryService;


@Service("integrationChangeHistoryService" )
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity> implements IntegrationChangeHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<IntegrationChangeHistoryEntity> list = this.list(new QueryWrapper<IntegrationChangeHistoryEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}