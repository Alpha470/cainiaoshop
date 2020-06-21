package com.alpha.cainiaoshop.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alpha.common.valid.Addgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.cainiaoshop.product.entity.BrandEntity;
import com.alpha.cainiaoshop.product.service.BrandService;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.R;

import javax.validation.Valid;
import javax.xml.ws.Binding;
import javax.xml.ws.RespectBinding;


/**
 * 品牌
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-25 20:50:29
 */
@RestController
@RequestMapping("product/brand" )
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}" )
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId" ) Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }
    /**
     * 新增
     */
    @RequestMapping("/add" )
    //@RequiresPermissions("product:brand:save")
    public R add(@Validated(Addgroup.class) @RequestBody BrandEntity brand) {
        brandService.save(brand);
        return R.ok();
    }
    /**
     * 保存
     */
    @RequestMapping("/save" )
    //@RequiresPermissions("product:brand:save")
    public R save(@Valid @RequestBody BrandEntity brand) {
//        if (result.hasErrors()) {
//            HashMap<String, String> hashMap = new HashMap<>();
//            result.getFieldErrors().forEach((item) -> {
//                        String defaultMessage = item.getDefaultMessage();
//                        String field = item.getField();
//                        hashMap.put(field, defaultMessage);
//                    }
//            );
//          return   R.error(400,"数据不合法").put("data",hashMap);
//
//        }
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody BrandEntity brand) {
        brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
