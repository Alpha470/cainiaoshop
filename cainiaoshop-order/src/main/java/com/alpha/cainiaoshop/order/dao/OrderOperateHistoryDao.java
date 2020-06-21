package com.alpha.cainiaoshop.order.dao;

import com.alpha.cainiaoshop.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:21:22
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {

}
