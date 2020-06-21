package com.alpha.cainiaoshop;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CainiaoshopThirdPartyApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G8LUsSbgzw5E2funWT3";
        String accessKeySecret = "arZ8FsCiNFaqGjyIJX4IyhVRtudcoI";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        InputStream inputStream = new FileInputStream("/Users/guojun/Desktop/qqq.png");
        ossClient.putObject("cainiaoshop-product", "ceshi4.jpg", inputStream);
// 关闭OSSClient。
        ossClient.shutdown();

    }

}
