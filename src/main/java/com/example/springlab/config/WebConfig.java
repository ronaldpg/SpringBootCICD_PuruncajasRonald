package com.example.springlab.config;

import com.example.springlab.interceptor.RequestLogginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLogginInterceptor logginInterceptor;

    //Registrar el interceptor para todas las rutas
    public void addInterceptors (InterceptorRegistry registry){
        registry.addInterceptor((logginInterceptor)).addPathPatterns("/api/**");
    }
}