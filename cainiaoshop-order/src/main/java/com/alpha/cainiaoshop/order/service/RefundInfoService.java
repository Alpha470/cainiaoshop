package com.alpha.cainiaoshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:21:22
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

