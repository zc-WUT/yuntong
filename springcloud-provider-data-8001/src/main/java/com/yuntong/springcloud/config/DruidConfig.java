package com.yuntong.springcloud.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;

@Configuration
public class DruidConfig {


    //后台监控
    //这个贼流弊
    //因为springboot内置了servlet容器，所以没有web.xml，替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录
        HashMap<String, String> initParams = new HashMap<>();
        //增加配置
        initParams.put("loginUsername","yuntong"); //固定配置loginUsername等
        initParams.put("loginPassword","yuntong");

        //允许访问者
        initParams.put("allow","");

        bean.setInitParameters(initParams);
        return bean;
    }

    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤那些请求呢：
        HashMap<String,String> initParameters = new HashMap<>();
        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*,/plugins/**,*.jpg");
        System.out.println();
        return bean;
    }
}
