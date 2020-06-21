package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.vo.AttrgroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.AttrAttrgroupRelationDao;
import com.alpha.cainiaoshop.product.entity.AttrAttrgroupRelationEntity;
import com.alpha.cainiaoshop.product.service.AttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService" )
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<AttrAttrgroupRelationEntity> list = this.list(new QueryWrapper<AttrAttrgroupRelationEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public void saveBech(List<AttrgroupRelationVo> vos) {
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map(item -> {
            AttrAttrgroupRelationEntity attrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vos, attrgroupRelationEntity);
            return attrgroupRelationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}