package com.crazycode.config;

import com.crazycode.interceptor.MyInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class MyViewControlConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把我们自定义的拦截器对象放到SpringMVC中的注册器中
        //没有自定访问的url,
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
        //指定拦截的url
        interceptorRegistration.addPathPatterns("/queryAll");
        //指定排除的url不被拦截
        // interceptorRegistration.excludePathPatterns("");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index.html").setViewName("/index");
    }
}
