package com.alpha.cainiaoshop.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.ware.dao.WareOrderTaskDao;
import com.alpha.cainiaoshop.ware.entity.WareOrderTaskEntity;
import com.alpha.cainiaoshop.ware.service.WareOrderTaskService;


@Service("wareOrderTaskService" )
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<WareOrderTaskEntity> list = this.list(new QueryWrapper<WareOrderTaskEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

}