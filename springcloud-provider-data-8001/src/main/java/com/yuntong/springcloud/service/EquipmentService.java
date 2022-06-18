package com.yuntong.springcloud.service;

import com.yuntong.springcloud.domain.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
public interface EquipmentService extends IService<Equipment> {

    List<Equipment> findAll(int page, int size) throws Exception;

    List<Equipment> findByProductNum(String productNum) throws Exception;

    void addEquip(Equipment equip) throws Exception;

    void delEquip(int equipId) throws Exception;

    void updateEquip(Equipment equip) throws Exception;
}
