package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberDao;
import com.alpha.cainiaoshop.member.entity.MemberEntity;
import com.alpha.cainiaoshop.member.service.MemberService;


@Service("memberService" )
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberEntity> list = this.list(new QueryWrapper<MemberEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}