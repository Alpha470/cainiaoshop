package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberReceiveAddressDao;
import com.alpha.cainiaoshop.member.entity.MemberReceiveAddressEntity;
import com.alpha.cainiaoshop.member.service.MemberReceiveAddressService;


@Service("memberReceiveAddressService" )
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberReceiveAddressEntity> list = this.list(new QueryWrapper<MemberReceiveAddressEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}