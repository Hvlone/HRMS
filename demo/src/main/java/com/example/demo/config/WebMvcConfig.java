package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FileProperties fileProperties;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/includes/**").addResourceLocations("classpath:/templates/includes/");
        /*registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + fileProperties.getUploadDir() + "/");*/
        // 获取当前工作目录的绝对路径
        String currentDir = System.getProperty("user.dir");
        String uploadsPath = "file:" + currentDir + "/uploads/";

        // 映射 /uploads/** 到本地的 uploads/ 目录
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadsPath);
    }
}

