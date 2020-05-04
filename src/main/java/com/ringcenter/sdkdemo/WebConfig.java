package com.ringcenter.sdkdemo;

import com.ringcenter.sdkdemo.interceptor.CORSInterceptor;
import com.ringcenter.sdkdemo.interceptor.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CORSInterceptor corsInterceptor;

    @Autowired
    private ControllerInterceptor controllerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(controllerInterceptor);
    }
}
