package com.ringcenter.sdkdemo;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.ringcenter.sdkdemo")
class SdkDemoServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
