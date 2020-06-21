package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.entity.*;
import com.alpha.cainiaoshop.product.feign.CouponFeignService;
import com.alpha.cainiaoshop.product.service.*;
import com.alpha.cainiaoshop.product.vo.*;
import com.alpha.common.to.SkuReduceTo;
import com.alpha.common.to.SpuBoundTo;
import com.alpha.common.utils.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;

import com.alpha.cainiaoshop.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SpuInfoDescService saveSpuInfoDesc;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    AttrService attrService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    CouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SpuInfoEntity> list = this.list(new QueryWrapper<SpuInfoEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        //1.保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);

        //2保存spu描述图片pms_spu_info_desc
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",", spuSaveVo.getDecript()));
        saveSpuInfoDesc.saveSpuInfoDesc(spuInfoDescEntity);

        //3保存spu图片集pms_spu_images
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);


        //4。保存spu的规格参数pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity attrEntity = attrService.getById(attr.getAttrId());
            if (attrEntity != null) {
                valueEntity.setAttrName(attrEntity.getAttrName());
            }
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setSpuId(spuInfoEntity.getId());
            valueEntity.setQuickShow(attr.getShowDesc());
            return valueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(collect);

        //5。保存spu积分信息sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds, spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        couponFeignService.saveSpuBounds(spuBoundTo);

        //6。保存当前spu对应的sku信息
        //6.1、sku基本信息 pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                String defaultImages = "";
                for (SkuImagesEntity image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImages = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImages);
                skuInfoService.saveSkuInfo(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> collect1 = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());

                    return skuImagesEntity;
                }).filter(entity -> {
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());

                //6.2、 sku图片信息 pms_sku_images
                skuImagesService.saveBatch(collect1);

                //6.3、 sku销售属性 pms_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> collect2 = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(collect2);
                //调用远程feign
                //6.4、 sku优惠，满减信息 sms_sku_ladder\sms_full_reduction\sms_member_price
                SkuReduceTo skuReduceTo = new SkuReduceTo();
                BeanUtils.copyProperties(item, skuReduceTo);
                skuReduceTo.setSkuId(skuId);

                //过滤垃圾数据
                if (skuReduceTo.getFullCount() > 0 || skuReduceTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
                    couponFeignService.saveSkuReduction(skuReduceTo);
                }

            });
        }

    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.save(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        /**
         * status: 0
         * key:
         * brandId: 3
         * catelogId: 225
         */
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)){
            wrapper.and(w->{
                w.eq("id",key).or().like("spu_name",key);
            });
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId)){
            wrapper.eq("brand_Id",brandId);
        }
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("publish_status",status);
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId)){
            wrapper.eq("catalog_Id",catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

}