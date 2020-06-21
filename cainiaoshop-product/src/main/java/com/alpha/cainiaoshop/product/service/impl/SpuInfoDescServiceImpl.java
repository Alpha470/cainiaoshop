package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.SpuInfoDescDao;
import com.alpha.cainiaoshop.product.entity.SpuInfoDescEntity;
import com.alpha.cainiaoshop.product.service.SpuInfoDescService;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {
    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SpuInfoDescEntity> list = this.list(new QueryWrapper<SpuInfoDescEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity spuInfoDescEntity) {
        this.spuInfoDescService.save(spuInfoDescEntity);
    }

}