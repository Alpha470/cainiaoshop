package com.alpha.cainiaoshop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:28:55
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

