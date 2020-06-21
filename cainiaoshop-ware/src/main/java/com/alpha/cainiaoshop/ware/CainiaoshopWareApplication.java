package com.alpha.cainiaoshop.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CainiaoshopWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CainiaoshopWareApplication.class, args);
    }

}
