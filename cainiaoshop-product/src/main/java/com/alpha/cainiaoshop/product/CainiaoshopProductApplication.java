package com.alpha.cainiaoshop.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.alpha.cainiaoshop.product.feign")
@EnableDiscoveryClient
@MapperScan("com.alpha.cainiaoshop.product.dao" )
@SpringBootApplication
public class CainiaoshopProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(CainiaoshopProductApplication.class, args);
    }

}
