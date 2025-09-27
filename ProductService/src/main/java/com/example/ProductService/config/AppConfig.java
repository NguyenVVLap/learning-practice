package com.example.ProductService.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class AppConfig {
    // Default
    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

    // Customer
//    @Bean
//    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
//        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
//                = new FilterRegistrationBean<>( new ShallowEtagHeaderFilter());
//        filterRegistrationBean.addUrlPatterns("/products/*");
//        filterRegistrationBean.setName("etagFilter");
//        return filterRegistrationBean;
//    }
}
