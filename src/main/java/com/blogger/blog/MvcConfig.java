package com.blogger.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/entry").setViewName("entry");
//        registry.addViewController("/").setViewName("entry");
//        registry.addViewController("/").setViewName("index.html");
//        registry.addViewController("/login").setViewName("login");
    }

}
