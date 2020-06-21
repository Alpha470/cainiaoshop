package com.alpha.cainiaoshop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alpha.cainiaoshop.product.entity.BrandEntity;
import com.alpha.cainiaoshop.product.vo.BrandVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alpha.cainiaoshop.product.entity.CategoryBrandRelationEntity;
import com.alpha.cainiaoshop.product.service.CategoryBrandRelationService;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.R;


/**
 * 品牌分类关联
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@RestController
@RequestMapping("product/categorybrandrelation" )
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    //@RequestMapping(value = "/cateloglist/list" ,method = RequestMethod.GET)
    @GetMapping("/catelog/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R cateloglist(@RequestParam("brandId") Long brandId) {
        List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));

        return R.ok().put("data", data);
    }
    /**
     * 列表
     */
    @RequestMapping("/list" )
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * product/categorybrandrelation/brands/list
     *
     * coller 1接收请求 2传给service 3返回数据
     * service 处理业务逻辑
     * @param catId
     * @return
     */

    @GetMapping("/brands/list")
    public R relationBrandList(@RequestParam("catId") Long catId){
           List<BrandEntity> vos=categoryBrandRelationService.getBrandsCatId(catId);
        List<BrandVo> collect = vos.stream().map(item -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setBrandName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());
        return R.ok().put("data",collect);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id" ) Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
