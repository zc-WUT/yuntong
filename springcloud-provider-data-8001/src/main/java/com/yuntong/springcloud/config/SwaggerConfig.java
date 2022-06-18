package com.yuntong.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启swagger2
public class SwaggerConfig {

    @Bean
    //配置了swagger的docket的bean实例
    public Docket docket1(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过 environment.acceptsProfiles判断是否处在自己设定的环境
        boolean b = environment.acceptsProfiles(profiles);


        return  new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .groupName("张弛")
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage：指定要扫描的包
                //any()：扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解,参数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.yuntong.springcloud.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //path //过滤
//                .paths(PathSelectors.ant("/ngchi/**"))
                .build();

    }

    //配置多个Docter分组
    @Bean
    public Docket docket2(){
        return  new Docket(DocumentationType.SWAGGER_2).groupName("yuntong");
    }
    @Bean
    public Docket docket3(){
        return  new Docket(DocumentationType.SWAGGER_2).groupName("吴晓艳");
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
        Contact contact =new Contact("张弛","0","1049379728@qq.com");
        return new ApiInfo("我的API文档",
                "云童",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
