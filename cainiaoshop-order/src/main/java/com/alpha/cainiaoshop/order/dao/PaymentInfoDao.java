package com.alpha.cainiaoshop.order.dao;

import com.alpha.cainiaoshop.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:21:22
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {

}
