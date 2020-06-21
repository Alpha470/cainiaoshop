package com.alpha.cainiaoshop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alpha.cainiaoshop.product.entity.ProductAttrValueEntity;
import com.alpha.cainiaoshop.product.service.ProductAttrValueService;
import com.alpha.cainiaoshop.product.vo.AttrResoVo;
import com.alpha.cainiaoshop.product.vo.AttrVo;
import com.alpha.cainiaoshop.product.vo.AttrgroupRelationVo;
import com.alpha.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alpha.cainiaoshop.product.entity.AttrEntity;
import com.alpha.cainiaoshop.product.service.AttrService;
import com.alpha.common.utils.R;


/**
 * 商品属性
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@RestController
@RequestMapping("product/attr" )
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Autowired
    ProductAttrValueService productAttrValueService;

    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrListForSpu(@PathVariable("spuId") Long spuid){
       List<ProductAttrValueEntity> productAttrValueEntityList=productAttrValueService.baseAttrListForSpu(spuid);
        return R.ok().put("data",productAttrValueEntityList);
    }

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String,Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String type){
        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,type);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list" )
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}" )
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId" ) Long attrId) {
        //AttrEntity attr = attrService.getById(attrId);
        AttrResoVo resoVo=attrService.getAttrInfo(attrId);
        return R.ok().put("attr", resoVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVo attr) {
        //attrService.updateById(attr);
        attrService.updateAttr(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update/{spuId}" )
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entity) {
        //attrService.updateById(attr);
        productAttrValueService.updateSpuAttr(spuId,entity);
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete" )
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }


}
