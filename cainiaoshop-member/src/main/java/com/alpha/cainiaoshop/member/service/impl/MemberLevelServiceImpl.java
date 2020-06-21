package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberLevelDao;
import com.alpha.cainiaoshop.member.entity.MemberLevelEntity;
import com.alpha.cainiaoshop.member.service.MemberLevelService;


@Service("memberLevelService" )
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberLevelEntity> list = this.list(new QueryWrapper<MemberLevelEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}