package com.book.config;

import com.book.interceptor.PowerInterceptor;
import com.book.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private static final String[] all = {
            "/book/**",
            "/borrowrecord/**",
            "/user/**",
    };

    public static final String[] aboutLogin = {
            "/user/login",
            "/user/register",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token拦截器，拦截除用户登陆注册以外所有请求
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns(all)
                .excludePathPatterns(aboutLogin);

        // 权限拦截器，做权限拦截
        registry.addInterceptor(new PowerInterceptor())
                .addPathPatterns(all)
                .excludePathPatterns(aboutLogin);
    }
}
