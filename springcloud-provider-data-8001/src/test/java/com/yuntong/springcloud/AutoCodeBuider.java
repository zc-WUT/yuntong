package com.yuntong.springcloud;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class AutoCodeBuider {
    public static void main(String[] args) {
// 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
// 配置策略
// 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("张弛");
        gc.setOpen(false);
        gc.setFileOverride(false); // 是否覆盖
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
//2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://123.56.96.224:3310/user?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("4833666");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
//3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("springcloud");
        pc.setParent("com.yuntong");
        pc.setEntity("domain");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
//4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("role_permission","users_role");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；


        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute(); //执行
    }
}
