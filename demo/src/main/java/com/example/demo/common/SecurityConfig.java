package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()   // ← 所有请求一律放行
                )
                .csrf(csrf -> csrf.disable())   // 顺便关掉 CSRF，纯静态页面不需要
                .formLogin(form -> form.disable()) // 禁用登录过滤器
                .httpBasic(basic -> basic.disable()); // 禁用 Basic 认证框
        return http.build();
    }
}