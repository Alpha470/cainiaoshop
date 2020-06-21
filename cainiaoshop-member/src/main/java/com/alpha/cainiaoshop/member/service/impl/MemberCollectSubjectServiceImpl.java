package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberCollectSubjectDao;
import com.alpha.cainiaoshop.member.entity.MemberCollectSubjectEntity;
import com.alpha.cainiaoshop.member.service.MemberCollectSubjectService;


@Service("memberCollectSubjectService" )
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectDao, MemberCollectSubjectEntity> implements MemberCollectSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberCollectSubjectEntity> list = this.list(new QueryWrapper<MemberCollectSubjectEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}