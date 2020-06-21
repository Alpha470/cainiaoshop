package com.alpha.cainiaoshop.product.service;

import com.alpha.cainiaoshop.product.vo.AttrResoVo;
import com.alpha.cainiaoshop.product.vo.AttrVo;
import com.alpha.cainiaoshop.product.vo.AttrgroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.product.entity.AttrEntity;

import java.util.Map;
import java.util.List;

/**
 * 商品属性
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrResoVo getAttrInfo(Long attrId);
    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrgroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

}

