package com.alpha.cainiaoshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

