package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberStatisticsInfoDao;
import com.alpha.cainiaoshop.member.entity.MemberStatisticsInfoEntity;
import com.alpha.cainiaoshop.member.service.MemberStatisticsInfoService;


@Service("memberStatisticsInfoService" )
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoDao, MemberStatisticsInfoEntity> implements MemberStatisticsInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberStatisticsInfoEntity> list = this.list(new QueryWrapper<MemberStatisticsInfoEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}