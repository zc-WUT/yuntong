package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.TestData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface TestDataService extends IService<TestData> {

    List<TestData> findAll(String equipNum) throws Exception;

    List<TestData> findLargeOfEquip(String productNum) throws Exception;

    List<TestData> findMaxOfPro() throws Exception;

    TestData findMaxOfPro(String productNum) throws Exception;
}
