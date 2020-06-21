package com.alpha.cainiaoshop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:28:55
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);
}

