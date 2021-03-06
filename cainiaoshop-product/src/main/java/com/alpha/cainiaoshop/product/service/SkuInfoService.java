package com.alpha.cainiaoshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

