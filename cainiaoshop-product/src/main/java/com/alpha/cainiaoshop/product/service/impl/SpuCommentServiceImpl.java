package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.SpuCommentDao;
import com.alpha.cainiaoshop.product.entity.SpuCommentEntity;
import com.alpha.cainiaoshop.product.service.SpuCommentService;


@Service("spuCommentService" )
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentDao, SpuCommentEntity> implements SpuCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SpuCommentEntity> list = this.list(new QueryWrapper<SpuCommentEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}