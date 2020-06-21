package com.alpha.cainiaoshop.coupon.service.impl;

import com.alpha.cainiaoshop.coupon.entity.MemberPriceEntity;
import com.alpha.cainiaoshop.coupon.entity.SkuLadderEntity;
import com.alpha.cainiaoshop.coupon.service.MemberPriceService;
import com.alpha.cainiaoshop.coupon.service.SkuLadderService;
import com.alpha.common.to.MemberPrice;
import com.alpha.common.to.SkuReduceTo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.coupon.dao.SkuFullReductionDao;
import com.alpha.cainiaoshop.coupon.entity.SkuFullReductionEntity;
import com.alpha.cainiaoshop.coupon.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Autowired
    SkuLadderService skuLadderService;

    @Autowired
    MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SkuFullReductionEntity> list = this.list(new QueryWrapper<SkuFullReductionEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public void saveSkuReduction(SkuReduceTo skuReduceTo) {
        //1.保存满减打折
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        BeanUtils.copyProperties(skuReduceTo, skuLadderEntity);
        if (skuReduceTo.getFullCount()>0){

            skuLadderService.save(skuLadderEntity);
        }

        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReduceTo, skuFullReductionEntity);
        if (skuFullReductionEntity.getFullPrice().compareTo(new BigDecimal("0"))==1){

            this.save(skuFullReductionEntity);
        }

        List<MemberPrice> memberPrice = skuReduceTo.getMemberPrice();
        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(skuReduceTo.getSkuId());
            memberPriceEntity.setAddOther(1);
            memberPriceEntity.setMemberLevelId(item.getId());
            memberPriceEntity.setMemberLevelName(item.getName());
            memberPriceEntity.setMemberPrice(item.getPrice());
            return memberPriceEntity;
        }).filter(item->{
            return item.getMemberPrice().compareTo(new BigDecimal("0"))==1;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);
    }

}