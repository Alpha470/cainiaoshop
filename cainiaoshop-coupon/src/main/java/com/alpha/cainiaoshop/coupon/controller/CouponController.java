package com.alpha.cainiaoshop.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.cainiaoshop.coupon.entity.CouponEntity;
import com.alpha.cainiaoshop.coupon.service.CouponService;
import com.alpha.common.utils.PageUtils;
import com.alpha.common.utils.R;


/**
 * 优惠券信息
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:06:14
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon" )
public class CouponController {
    @Autowired
    private CouponService couponService;

    /**
     * 配置中心nacos
     *
     * @return
     */
    @Value("${coupon.user.name}" )
    private String username;
    @Value("${coupon.user.email}" )
    private String email;

    @RequestMapping("/getusername" )
    public R getuser() {
        return R.ok().put("username", username).put("email", email);
    }

    /**
     * 服务注册
     *
     * @return
     */
    @RequestMapping("/member/list" )
    public R coupon() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满200减30" );
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }

    /**
     * 列表
     */
    @RequestMapping("/list" )
    //@RequiresPermissions("coupon:coupon:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    //@RequiresPermissions("coupon:coupon:info")
    public R info(@PathVariable("id" ) Long id) {
        CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    //@RequiresPermissions("coupon:coupon:save")
    public R save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    //@RequiresPermissions("coupon:coupon:update")
    public R update(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    //@RequiresPermissions("coupon:coupon:delete")
    public R delete(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
