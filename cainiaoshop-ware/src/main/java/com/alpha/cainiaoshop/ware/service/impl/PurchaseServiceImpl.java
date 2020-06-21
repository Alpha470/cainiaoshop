package com.alpha.cainiaoshop.ware.service.impl;

import com.alpha.cainiaoshop.ware.entity.PurchaseDetailEntity;
import com.alpha.cainiaoshop.ware.service.PurchaseDetailService;
import com.alpha.cainiaoshop.ware.service.WareSkuService;
import com.alpha.cainiaoshop.ware.vo.MergeVo;
import com.alpha.cainiaoshop.ware.vo.PurchaseDoneVo;
import com.alpha.cainiaoshop.ware.vo.PurchaseItemDoneVo;
import com.alpha.common.constant.WareConstant;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.ware.dao.PurchaseDao;
import com.alpha.cainiaoshop.ware.entity.PurchaseEntity;
import com.alpha.cainiaoshop.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService" )
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {
@Autowired
    PurchaseDetailService purchaseDetailService;
@Autowired
    WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<PurchaseEntity> list = this.list(new QueryWrapper<PurchaseEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public PageUtils queryUnreceivePage(Map<String, Object> params) {
        QueryWrapper<PurchaseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("status", WareConstant.PurchaseStatusEnum.CREATED.getCode()).or().eq("status",WareConstant.PurchaseStatusEnum.ASSIGND.getCode());
        IPage<PurchaseEntity> page = this.page(new Query<PurchaseEntity>().getPage(params), wrapper);
        return new PageUtils(page);

    }

    @Override
    public void saveMerge(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId==null){
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }else{
            //TODO  先确认采购的状态正确
            List<Long> items = mergeVo.getItems();
            Long finalPurchaseId = purchaseId;
            List<PurchaseDetailEntity> collect = items.stream().map(i -> {
                PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
                purchaseDetailEntity.setId(i);
                purchaseDetailEntity.setPurchaseId(finalPurchaseId);
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGND.getCode());
                return purchaseDetailEntity;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(collect);
            PurchaseEntity purchaseEntity = this.getById(purchaseId);
            purchaseEntity.setUpdateTime(new Date());
            //purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.ASSIGND.getCode());
            this.updateById(purchaseEntity);
        }
    }

    @Transactional
    @Override
    public void received(List<Long> ids) {
        //更新状态，采购单状态，采购需求状态
        List<PurchaseEntity> collect = ids.stream().map(id -> {
            PurchaseEntity byId = this.getById(id);
            return byId;
        }).filter(item->{
            return (item.getStatus()==WareConstant.PurchaseStatusEnum.CREATED.getCode()
                    || item.getStatus()==WareConstant.PurchaseStatusEnum.ASSIGND.getCode());
        }).map(item->{
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            item.setUpdateTime(new Date());
            return item;
        }).collect(Collectors.toList());
        this.updateBatchById(collect);


        collect.forEach(item->{
            List<PurchaseDetailEntity> detailEntityList = purchaseDetailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetailEntity> collect1 = detailEntityList.stream().map(entity -> {
                entity.setStatus(WareConstant.PurchaseDetailStatusEnum.RECEIVE.getCode());

                return entity;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(collect1);
        });


    }

    @Transactional
    @Override
    public void done(PurchaseDoneVo vo) {
        // 2.改变采购项状态
        List<PurchaseItemDoneVo> items = vo.getItems();
        ArrayList<PurchaseDetailEntity> updates = new ArrayList<>();
        Boolean flag = true;
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
            if (Objects.equals(item.getStatus(), WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode())) {
                flag = false;
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode());
            } else {
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                // 3.将成功采购的进行入库
                PurchaseDetailEntity byId = purchaseDetailService.getById(item.getItemId());
                wareSkuService.addStock(byId.getSkuId(), byId.getWareId(), byId.getSkuNum());
            }
            purchaseDetailEntity.setId(item.getItemId());
            updates.add(purchaseDetailEntity);
        }
        purchaseDetailService.updateBatchById(updates);

        // 1.改变采购单状态
        Long id = vo.getId();
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag?WareConstant.PurchaseStatusEnum.FINISH.getCode():WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);

    }


}