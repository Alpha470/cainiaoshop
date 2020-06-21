package com.alpha.cainiaoshop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.Query;

import com.alpha.cainiaoshop.product.dao.SpuImagesDao;
import com.alpha.cainiaoshop.product.entity.SpuImagesEntity;
import com.alpha.cainiaoshop.product.service.SpuImagesService;


@Service("spuImagesService" )
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<SpuImagesEntity> list = this.list(new QueryWrapper<SpuImagesEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Override
    public void saveImages(Long id, List<String> images) {
        if (images==null || images.size()==0){

        }else {
            List<SpuImagesEntity> collect = images.stream().map(item -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(item);
                return spuImagesEntity;

            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }
    }

}