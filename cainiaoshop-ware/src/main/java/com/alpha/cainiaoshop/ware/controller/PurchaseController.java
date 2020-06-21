package com.alpha.cainiaoshop.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alpha.cainiaoshop.ware.vo.MergeVo;
import com.alpha.cainiaoshop.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alpha.cainiaoshop.ware.entity.PurchaseEntity;
import com.alpha.cainiaoshop.ware.service.PurchaseService;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.R;


/**
 * 采购信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:28:55
 */
@RestController
@RequestMapping("ware/purchase" )
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    /**
     * 完成采购单
     */
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVo vo) {
        purchaseService.done(vo);
        return R.ok();
    }
    /**
     * 领取采购[1,2,3,4]//采购单id
     */
    @PostMapping("/received" )
    //@RequiresPermissions("ware:purchase:list")
    public R received(@RequestBody List<Long> ids) {
        purchaseService.received(ids);

        return R.ok();
    }
    /**
     * 合并
     */
    @RequestMapping("/merge" )
    //@RequiresPermissions("ware:purchase:list")
    public R merge(@RequestBody  MergeVo mergeVo) {
        purchaseService.saveMerge(mergeVo);

        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/unreceive/list" )
    //@RequiresPermissions("ware:purchase:list")
    public R unreceivelist(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryUnreceivePage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list" )
    //@RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    //@RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id" ) Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    //@RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase) {
        purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    //@RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase) {
        purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
