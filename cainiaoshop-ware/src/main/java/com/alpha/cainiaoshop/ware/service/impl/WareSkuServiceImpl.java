package com.alpha.cainiaoshop.ware.service.impl;

import com.alpha.cainiaoshop.ware.fegin.ProductFeignService;
import com.alpha.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.ware.dao.WareSkuDao;
import com.alpha.cainiaoshop.ware.entity.WareSkuEntity;
import com.alpha.cainiaoshop.ware.service.WareSkuService;
import org.springframework.util.StringUtils;


@Service("wareSkuService" )
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {
    @Autowired
    WareSkuDao wareSkuDao;

    @Autowired
    ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        /**
         * skuId: 2
         * wareId: 1
         */
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)){
            wrapper.eq("sku_Id",skuId);
        }
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)){
            wrapper.eq("ware_Id",wareId);
        }
        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }



    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {

        // 判断库存记录是否存在
        List<WareSkuEntity> entities = this.baseMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities == null || entities.size() == 0) {
            // 添加
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            try {
                R r = productFeignService.info(skuId);
                if (r.getCode() == 0) {
                    Map<String, Object> data = (Map<String, Object>) r.get("skuInfo");
                    wareSkuEntity.setSkuName((String) data.get("skuName"));
                }
            } catch (Exception e) {

            }
            this.baseMapper.insert(wareSkuEntity);
        } else {
            this.baseMapper.addStock(skuId, wareId, skuNum);
        }

    }

}