package com.alpha.cainiaoshop.product.service.impl;

import com.alpha.cainiaoshop.product.dao.AttrAttrgroupRelationDao;
import com.alpha.cainiaoshop.product.dao.AttrGroupDao;
import com.alpha.cainiaoshop.product.dao.CategoryDao;
import com.alpha.cainiaoshop.product.entity.AttrAttrgroupRelationEntity;
import com.alpha.cainiaoshop.product.entity.AttrGroupEntity;
import com.alpha.cainiaoshop.product.entity.CategoryEntity;
import com.alpha.cainiaoshop.product.service.CategoryService;
import com.alpha.cainiaoshop.product.vo.AttrResoVo;
import com.alpha.cainiaoshop.product.vo.AttrVo;
import com.alpha.cainiaoshop.product.vo.AttrgroupRelationVo;
import com.alpha.common.constant.ProductConstant;
import com.alpha.common.utils.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alpha.common.utils.PageUtils;

import com.alpha.cainiaoshop.product.dao.AttrDao;
import com.alpha.cainiaoshop.product.entity.AttrEntity;
import com.alpha.cainiaoshop.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrgroupRelationDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    AttrGroupDao attrGroupDao;
    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<AttrEntity> list = this.list(new QueryWrapper<AttrEntity>());
        return new PageUtils(list, 0, 0, 0);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);

        this.save(attrEntity);
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wapper) -> {
                queryWrapper.eq("attr_id", key).or().like("attr_name", key);

            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrResoVo> respVos = records.stream().map((attrEntity) -> {
            AttrResoVo attrResoVo = new AttrResoVo();
            BeanUtils.copyProperties(attrEntity, attrResoVo);
            if ("base".equalsIgnoreCase(type)) {
                AttrAttrgroupRelationEntity attrId = attrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (attrId != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrResoVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }

            }
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            attrResoVo.setCatelogName(categoryEntity.getName());
            return attrResoVo;
        }).collect(Collectors.toList());
        pageUtils.setList(respVos);
        return pageUtils;
    }

    @Override
    public AttrResoVo getAttrInfo(Long attrId) {
        AttrResoVo resoVo = new AttrResoVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, resoVo);
        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrgroupRelationEntity = attrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            if (attrgroupRelationEntity != null) {
                resoVo.setAttrGroupId(attrgroupRelationEntity.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupRelationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    resoVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        //设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        resoVo.setCatelogPath(catelogPath);

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if (categoryEntity != null) {
            resoVo.setCatelogName(categoryEntity.getName());
        }
        return resoVo;

    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        //修改关联分组
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelationEntity attrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrgroupRelationEntity.setAttrId(attr.getAttrId());
            attrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            Integer count = attrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if (count > 0) {
                attrgroupRelationDao.update(attrgroupRelationEntity, new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));

            } else {
                attrgroupRelationDao.insert(attrgroupRelationEntity);

            }
        }

    }

    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = attrgroupRelationDao.selectList(new
                QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        List<Long> list = entities.stream().map(entitie -> {
            return entitie.getAttrId();
        }).collect(Collectors.toList());
        if (list==null && list.size()==0){
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(list);

        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrgroupRelationVo[] vos) {
        List<AttrAttrgroupRelationEntity> relationEntities = Arrays.asList(vos).stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        attrgroupRelationDao.deleteBathRelation(relationEntities);

    }

    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        //当前分组只能关联自己所属分类的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        //当前分组只能关联别的分组没有引用的属性
        Long catelogId = attrGroupEntity.getCatelogId();
        //找到分类下的其他分组
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> collect = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());
        //这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = attrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        //从当前分类的所有属性中移除这些属性
        QueryWrapper<AttrEntity> wapper = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type",ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (attrIds != null && attrIds.size() > 0) {
            wapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wapper.and((w) -> {
                w.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wapper);
        return new PageUtils(page);
    }


}