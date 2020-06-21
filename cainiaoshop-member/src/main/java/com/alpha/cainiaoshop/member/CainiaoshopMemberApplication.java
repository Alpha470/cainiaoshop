package com.alpha.cainiaoshop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.alpha.cainiaoshop.member.feign" )
@EnableDiscoveryClient
@SpringBootApplication
public class CainiaoshopMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CainiaoshopMemberApplication.class, args);
    }

}
