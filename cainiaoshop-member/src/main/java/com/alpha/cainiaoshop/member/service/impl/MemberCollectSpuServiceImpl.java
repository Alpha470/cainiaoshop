package com.alpha.cainiaoshop.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.member.dao.MemberCollectSpuDao;
import com.alpha.cainiaoshop.member.entity.MemberCollectSpuEntity;
import com.alpha.cainiaoshop.member.service.MemberCollectSpuService;


@Service("memberCollectSpuService" )
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuDao, MemberCollectSpuEntity> implements MemberCollectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<MemberCollectSpuEntity> list = this.list(new QueryWrapper<MemberCollectSpuEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}