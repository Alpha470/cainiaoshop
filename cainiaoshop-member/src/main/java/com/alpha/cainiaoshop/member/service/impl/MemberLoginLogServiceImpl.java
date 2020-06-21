package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberLoginLogDao;
import com.alpha.cainiaoshop.member.entity.MemberLoginLogEntity;
import com.alpha.cainiaoshop.member.service.MemberLoginLogService;


@Service("memberLoginLogService" )
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity> implements MemberLoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberLoginLogEntity> list = this.list(new QueryWrapper<MemberLoginLogEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}