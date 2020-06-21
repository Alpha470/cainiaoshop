package com.alpha.cainiaoshop.ware.service;

import com.alpha.cainiaoshop.ware.vo.MergeVo;
import com.alpha.cainiaoshop.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.ware.entity.PurchaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:28:55
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUnreceivePage(Map<String, Object> params);

    void saveMerge(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseDoneVo vo);
}

