package com.yuntong.springcloud.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntong.springcloud.domain.EquipTimeData;
import com.yuntong.springcloud.domain.Equipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张弛
 * @since 2020-08-24
 */
@Repository
public interface EquipmentMapper extends BaseMapper<Equipment> {

}
