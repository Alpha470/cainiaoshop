package com.alpha.cainiaoshop.product;


import com.alpha.cainiaoshop.product.entity.BrandEntity;
import com.alpha.cainiaoshop.product.service.BrandService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CainiaoshopProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("2我的" );
        brandEntity.setName("wos" );
        BrandEntity brand = brandService.getById(1);
        System.out.println(brand);
    }

}
