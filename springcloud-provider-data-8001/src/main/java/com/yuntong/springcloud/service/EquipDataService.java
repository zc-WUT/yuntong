package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.EquipData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuntong.springcloud.domain.EquipTimeData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface EquipDataService extends IService<EquipData> {

    List<EquipTimeData> findDataOfWorkTime(String productNum) throws Exception;

    List<EquipData> findDataOfContainer(String productNum) throws Exception;

    List<EquipData> findContainerDataByEquipNum(String equipNum) throws Exception;

    List<EquipTimeData> findTimeDataByEquipNum(String equipNum) throws Exception;
}
