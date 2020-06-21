package com.alpha.cainiaoshop.product.feign;


import com.alpha.common.to.SkuReduceTo;
import com.alpha.common.to.SpuBoundTo;
import com.alpha.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cainiaoshop-coupon")
public interface CouponFeignService {


    @PostMapping("/coupon/spubounds/save" )
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo" )
    R saveSkuReduction(@RequestBody  SkuReduceTo skuReduceTo);
}
