package com.alpha.cainiaoshop.coupon.service;

import com.alpha.common.to.SkuReduceTo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:06:14
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReduceTo skuReduceTo);
}

