package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.dao.EquipmentMapper;
import com.yuntong.springcloud.dao.ProductMapper;
import com.yuntong.springcloud.domain.Equipment;
import com.yuntong.springcloud.domain.Product;
import com.yuntong.springcloud.domain.TestData;
import com.yuntong.springcloud.dao.TestDataMapper;
import com.yuntong.springcloud.service.TestDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Service
@Transactional
public class TestDataServiceImpl extends ServiceImpl<TestDataMapper, TestData> implements TestDataService {

    @Autowired
    private TestDataMapper testDataMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 工况预警的第三层，查询全部的当前equipnum对应的数据
     * @param equipNum
     * @return
     * @throws Exception
     */
    @Override
    public List<TestData> findAll(String equipNum) throws Exception {
        QueryWrapper<TestData> wrapper = new QueryWrapper<>();
        wrapper.eq("equipnum", equipNum);
        return testDataMapper.selectList(wrapper);
    }

    /**
     * 工况预警的第二层
     * @param productNum
     * @return
     * @throws Exception
     */
    @Override
    public List<TestData> findLargeOfEquip(String productNum) throws Exception {
        //先从equipment表中查询productNum所对应的equipNum,全部封装到List集合中
        QueryWrapper<Equipment> wrapper1 = new QueryWrapper<>();
        wrapper1.select("equipnum","id").eq("productnum", productNum);
        //从testData中查询equipnum为equipNum的数据里的-->testdata最大的数据
        List<TestData> testDatas = new ArrayList<>();
        for (Equipment equipment : equipmentMapper.selectList(wrapper1)) {
            TestData testData = new TestData();
            String equipNum = equipment.getEquipnum(); //获取一个equipment对象的equipum属性值
            //通过自己写的sql从test_data表中查询出该equipnum所对应的数据集中的最大的testdata
            Double maxValue = testDataMapper.selectMaxEquipData(equipNum);
            testData.setId(equipment.getId()).setEquipnum(equipNum).setTestdata(maxValue);
            testDatas.add(testData);
        }
        return testDatas;
    }

    /**
     * 工况预警的第一层
     * @return
     * @throws Exception
     */
    @Override
    public List<TestData> findMaxOfPro() throws Exception {
        int i =1;
        //先获得全部的productNum
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.select("productnum","id");
        List<TestData> testDataList =new ArrayList<>();
        for (Product product : productMapper.selectList(wrapper)) {
            //在test_data中查询每一个productnum对应的最大testdata
            TestData testData=new TestData();
            testData.setId(product.getId()).setProductnum(product.getProductnum()).setTestdata(testDataMapper.selectMax(product.getProductnum()));
            testDataList.add(testData);
        }
        return testDataList;
    }

    /**
     * 用于首页的设备检测设备直观展示
     * @param productNum
     * @return
     * @throws Exception
     */
    @Override
    public TestData findMaxOfPro(String productNum) throws Exception {
        TestData testData =new TestData();
        testData.setTestdata(testDataMapper.selectMax(productNum));
        return testData;
    }
}
