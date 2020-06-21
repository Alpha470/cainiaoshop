package com.alpha.cainiaoshop.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@EnableDiscoveryClient
@SpringBootApplication
public class CainiaoshopCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CainiaoshopCouponApplication.class, args);
    }

}
