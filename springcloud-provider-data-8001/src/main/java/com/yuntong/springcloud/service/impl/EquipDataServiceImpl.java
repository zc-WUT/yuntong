package com.yuntong.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.dao.EquipTimeDataMapper;
import com.yuntong.springcloud.dao.EquipmentMapper;
import com.yuntong.springcloud.domain.EquipData;
import com.yuntong.springcloud.dao.EquipDataMapper;
import com.yuntong.springcloud.domain.EquipTimeData;
import com.yuntong.springcloud.domain.Equipment;
import com.yuntong.springcloud.service.EquipDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class EquipDataServiceImpl extends ServiceImpl<EquipDataMapper, EquipData> implements EquipDataService {

    @Autowired
    private EquipDataMapper equipDataMapper;
    @Autowired
    private EquipTimeDataMapper equipTimeDataMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 1.通过前端传入的productNum参数去equipment表中查询productNum对应的全部equipNum
     * 2.将要查询的第一列WORKTIME与对应的所有euipNum都写入到equipNums集合当中
     * 3.将集合里的每一个值都分割成一个独立的字符串，对应表里的每一个字段，进行查询
     * 4.查询结果是：Columns: WORKTIME, QC010101, QC010102, QC010103, QC010104, QC010201, QC010202, QC010203, QC010204, QC010301, QC010302这几列的数据
     *
     * @param productNum
     * @return
     * @throws Exception
     */
    @Override
    public List<EquipTimeData> findDataOfWorkTime(String productNum) throws Exception {
        QueryWrapper<Equipment> wrapper1 = new QueryWrapper<>();
        wrapper1.select("equipNum").eq("productNum", productNum);
        List<String> equipNums = new ArrayList<>();
        equipNums.add("WORKTIME"); //将固定列加入到数组中
        for (Equipment equipment : equipmentMapper.selectList(wrapper1)) {
            equipNums.add(equipment.getEquipnum());
        }
        QueryWrapper<EquipTimeData> wrapper2 = new QueryWrapper<>();
        //去除[,] 这三个符号后得到QC010101 QC010102 QC010103 QC010104 QC010201 QC010202 QC010203 QC010204 QC010301 QC010302
        String equipNumsToString = equipNums.toString().replaceAll("\\p{Punct}", "");
        //System.out.println(equipNumsToString);
        //equipNumsToString.split("\\s+")----> QC010101, QC010102, QC010103, QC010104, QC010201, QC010202, QC010203, QC010204, QC010301, QC010302
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        return equipTimeDataMapper.selectList(wrapper2);
    }

    /**
     * 1.通过前端传入的productNum参数去equipment表中查询productNum对应的全部equipNum
     * 2.将要查询的第一列CONTAINERNUM与对应的所有euipNum都写入到equipNums集合当中
     * 3.将集合里的每一个值都分割成一个独立的字符串，对应表里的每一个字段，进行查询
     * 4.查询结果是：Columns: CONTAINERNUM, QC010101, QC010102, QC010103, QC010104, QC010201, QC010202, QC010203, QC010204, QC010301, QC010302这几列的数据
     *
     * @param productNum
     * @return
     * @throws Exception
     */
    @Override
    public List<EquipData> findDataOfContainer(String productNum) throws Exception {
        QueryWrapper<Equipment> wrapper1 = new QueryWrapper<>();
        wrapper1.select("equipNum").eq("productNum", productNum);
        List<String> equipNums = new ArrayList<>();
        equipNums.add("CONTAINERNUM"); //将固定列加入到数组中
        for (Equipment equipment : equipmentMapper.selectList(wrapper1)) {
            equipNums.add(equipment.getEquipnum());
        }
        QueryWrapper<EquipData> wrapper2 = new QueryWrapper<>();
        //去除[,] 这三个符号后得到QC010101 QC010102 QC010103 QC010104 QC010201 QC010202 QC010203 QC010204 QC010301 QC010302
        String equipNumsToString = equipNums.toString().replaceAll("\\p{Punct}", "");
        //System.out.println(equipNumsToString);
        //equipNumsToString.split("\\s+")----> QC010101, QC010102, QC010103, QC010104, QC010201, QC010202, QC010203, QC010204, QC010301, QC010302
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        return equipDataMapper.selectList(wrapper2);
    }

    @Override
    public List<EquipData> findContainerDataByEquipNum(String equipNum) throws Exception {
        List<String> equipNums = new ArrayList<>();
        equipNums.add(equipNum);
        equipNums.add("CONTAINERNUM"); //将固定列加入到数组中
        QueryWrapper<EquipData> wrapper2 = new QueryWrapper<>();
        String equipNumsToString = equipNums.toString().replaceAll("\\p{Punct}", "");
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        return equipDataMapper.selectList(wrapper2);
    }

    @Override
    public List<EquipTimeData> findTimeDataByEquipNum(String equipNum) throws Exception {
        List<String> equipNums = new ArrayList<>();
        equipNums.add(equipNum);
        equipNums.add("WORKTIME"); //将固定列加入到数组中
        QueryWrapper<EquipTimeData> wrapper2 = new QueryWrapper<>();
        String equipNumsToString = equipNums.toString().replaceAll("\\p{Punct}", "");
        wrapper2.select(equipNumsToString.split("\\s+"));//把equipNums的集合里每一个参数都传入该方法中
        return equipTimeDataMapper.selectList(wrapper2);
    }
}
