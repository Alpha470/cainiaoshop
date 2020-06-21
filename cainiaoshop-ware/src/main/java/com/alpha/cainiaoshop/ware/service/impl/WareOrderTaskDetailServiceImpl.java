package com.alpha.cainiaoshop.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.ware.dao.WareOrderTaskDetailDao;
import com.alpha.cainiaoshop.ware.entity.WareOrderTaskDetailEntity;
import com.alpha.cainiaoshop.ware.service.WareOrderTaskDetailService;


@Service("wareOrderTaskDetailService" )
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<WareOrderTaskDetailEntity> list = this.list(new QueryWrapper<WareOrderTaskDetailEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}