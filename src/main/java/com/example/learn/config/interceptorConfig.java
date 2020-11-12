package com.example.learn.config;

import com.example.learn.interceptors.JWTInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] exclude = new String[]{
      "/auth/login",
      "/auth/register",

      // image
      "/**/*.png",

      // -- swagger ui
      "/csrf",
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**"
    };
    registry.addInterceptor(new JWTInterceptor())
      .addPathPatterns("/**")
      .excludePathPatterns(exclude);
  }
}
