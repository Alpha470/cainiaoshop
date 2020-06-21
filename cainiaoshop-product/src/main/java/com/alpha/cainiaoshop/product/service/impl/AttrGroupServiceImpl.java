package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.entity.AttrEntity;
import com.alpha.cainiaoshop.product.service.AttrService;
import com.alpha.cainiaoshop.product.vo.AttrGroupWithAttrsVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.alpha.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;


import com.alpha.cainiaoshop.product.dao.AttrGroupDao;
import com.alpha.cainiaoshop.product.entity.AttrGroupEntity;
import com.alpha.cainiaoshop.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService" )
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long cateLogId) {
        String queryKey = (String) params.get("key");

        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
        //catelog_id == 0时，按照attr_group_id和attr_group_name进行模糊查询，否则要带上catelog_id
        if(cateLogId != 0){
            queryWrapper.eq("catelog_id", cateLogId);
        }
        //select * from pms_attr_group WHERE catelog_id = 1 AND (attr_group_id =key or attr_group_name LIKE '%key%');
        if (!StringUtils.isEmpty(queryKey)) {

            queryWrapper.and((param) -> {
                param.eq("attr_group_id", queryKey)
                        .or()
                        .like("attr_group_name", queryKey);
            });
        }

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
        }

    @Override
    public  List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        List<AttrGroupEntity> attrGroupEnties = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<AttrGroupWithAttrsVo> collect = attrGroupEnties.stream().map(item -> {
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(item, attrGroupWithAttrsVo);
            List<AttrEntity> relationAttr = attrService.getRelationAttr(attrGroupWithAttrsVo.getAttrGroupId());
            attrGroupWithAttrsVo.setAttrs(relationAttr);
            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());
        return collect;
    }


}