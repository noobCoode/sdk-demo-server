package com.ringcenter.sdkdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ringcenter.sdkdemo.repo.mapper.*")
public class SdkDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdkDemoServerApplication.class, args);
    }

}
