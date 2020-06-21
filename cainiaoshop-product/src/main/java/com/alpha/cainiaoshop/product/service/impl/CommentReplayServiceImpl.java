package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.CommentReplayDao;
import com.alpha.cainiaoshop.product.entity.CommentReplayEntity;
import com.alpha.cainiaoshop.product.service.CommentReplayService;


@Service("commentReplayService" )
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<CommentReplayEntity> list = this.list(new QueryWrapper<CommentReplayEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}