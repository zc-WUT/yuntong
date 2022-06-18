package com.yuntong.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version : V1.0
 * @ClassName: MyWebMvnConfig
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/2/25 21:58
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截请求"/","/index.html"
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("main");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/real-time-data.html").setViewName("real-time-data");
        registry.addViewController("/product-add.html").setViewName("product-add");
        registry.addViewController("/equip-add.html").setViewName("equip-add");
        registry.addViewController("/equipImage.html").setViewName("equipImage");
        registry.addViewController("/permission-add.html").setViewName("permission-add");
        registry.addViewController("/role-add.html").setViewName("role-add");
        registry.addViewController("/user-add.html").setViewName("user-add");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         * @Date： Create in 14:08 2017/12/20
         */
        registry.addResourceHandler("/image/**").addResourceLocations("file:///D:/upload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
