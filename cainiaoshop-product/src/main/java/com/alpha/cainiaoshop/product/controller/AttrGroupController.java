package com.alpha.cainiaoshop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alpha.cainiaoshop.product.entity.AttrEntity;
import com.alpha.cainiaoshop.product.service.AttrAttrgroupRelationService;
import com.alpha.cainiaoshop.product.service.AttrService;
import com.alpha.cainiaoshop.product.service.CategoryService;
import com.alpha.cainiaoshop.product.vo.AttrGroupWithAttrsVo;
import com.alpha.cainiaoshop.product.vo.AttrgroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alpha.cainiaoshop.product.entity.AttrGroupEntity;
import com.alpha.cainiaoshop.product.service.AttrGroupService;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.R;


/**
 * 属性分组
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    public R addRelation(@RequestBody List<AttrgroupRelationVo> vos) {
        relationService.saveBech(vos);
        return R.ok();
    }

    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", entities);

    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long categlogId) {
        //PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params, categlogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(catelogPath);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }
/**
 * /product/attrgroup/{catelogId}/withattr
 */
@GetMapping("/{catelogId}/withattr")
public R getAttrGroupWithAttrs(@PathVariable("catelogId")Long catelogId){
    List<AttrGroupWithAttrsVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
    System.out.println( "-----");
    return  R.ok().put("data",attrGroupVos);

}
    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/attr/relation/delete")
    //@RequiresPermissions("product:attr:delete")
    public R deleteRelation(@RequestBody AttrgroupRelationVo[] vos) {


        attrService.deleteRelation(vos);

        return R.ok();
    }


}
